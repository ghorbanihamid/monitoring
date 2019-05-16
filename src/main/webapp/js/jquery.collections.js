/*
 *	jQuery Collections plugin
 *	version: 0.04 (13 July 2009)
 *
 *	@Author: Mikkel Bergmann
 *
 *	This plugin manages collections of DOM elements completely
 *	independently of look and feel. It allows you to dynamically
 *	add and remove groups of elements based on a template from the
 *	DOM or from a text based template.
 *
 */
(function(jQuery)
{
    /*
		WIP:
		.	Allow data with row specific info, such as "do we show the delete button"?.
		PS:		At the moment, the "beforeRemove" function will allow you to prevent deletion of an item, but we really
				want to hide the button in most cases.
	*/

	jQuery.fn.collections = function( args ) {

        //--Added by Sina--{--

        //-- 0 Not Change
        //-- 1 Insert Mode
        //-- 2 Update Mode
        var eprocDefaults = {
            collectionName: "collection",
            templateId: "",
            addButtonId: "",
            deleteUrl: "",
            onChange: "",

            namePattern: args.collectionName + "[{number}].{name}",
            addButton: jQuery('#' + args.addButtonId),
            template: jQuery('#' + args.templateId),
            startCount: 0,
            minItems: 0,
            maxItems: 100,

            deleteFunction: function(template, args, data, deleteButton)
            {
                if (data['state'] != undefined && data['state'] != 1) {
                    var request = jQuery.ajax({

                        type: 'POST',
                        data: data,

                        url: args.deleteUrl,
                        dataType: 'text',

                        error: function()
                        {
                            alert('خطا در حذف رکورد مورد نظر');
                        },

                        success: function(html)
                        {
                            jQuery.fn.removeCollection(template, args, data);
                        }
                    });
                } else
                    jQuery.fn.removeCollection(template, args, data);
            }
        };
        //--}------

		//	Setup default args
		args = jQuery.extend({
			target: this.get(0),							//	The target of the collection manager
			template: null,									//	Element to use for cloning. Note: do NOT give any items IDs, they will be removed
			data: [{}],										//	Optionally include some data, default is 1 record
			startCount: 1,									//	What number to start the count from
			minItems: 1,									//	Minimum number of items to show at any time
			maxItems: 10,									//	Maximum number of items to show
			namePattern: "colection[{number}][{name}]",     //	Pattern for naming the form fields (for mapping to back-end objects)
			deleteButtonName: 'deleteButton',				//	Name for delete button to use in the template
			deleteFunction: function( template, args, data, deleteButton ) {			//	Delete function
				jQuery.fn.removeCollection( template, args, data );
			},
			addButton: null,								//	Button to assign the add function to
			bindings: {},									//	name: {event:function}. WIP: we may be able to get rid of the specific addButton and DeleteButton events here
			beforeAdd: null,								//	Runs before an item is added, arguments: data, args, if we return anything, it will be used as data
			afterAdd: null,									//	Runs after an item is added, arguments: data, args
			beforeRemove: null,								//	Runs before an item is removed - return false to cancel removal of item. Arguments: data, args
			afterRemove: null								//	Runs after an item is removed, arguments: data, args, wasRemoved
		}, eprocDefaults, args || {} );

		if( ! args.template || ! args.addButton || ! args.target )return this;			//	Fail if no template, addButton or element
		jQuery( args.addButton ).attr( 'collectionCount', args.startCount );			//	Set the inital count on the add button as an expando
		for( var x = args.data.length; x < args.minItems; x++ )args.data.push( {} );	//	Add empty options, if we don't have enough data

		//	Render the initial collection(s)
		for( var i in args.data )jQuery.fn.addCollection( args.data[i], args );

		//	Bind addButton to insert a blank option
		jQuery( args.addButton ).click( function() { jQuery.fn.addCollection( {"state": 1}, args ); } );

		return this;
	};

	//	Generate new name for an element, using the supplied pattern
	//	optionally escape the pattern characters, so it can be used in jQuery
	jQuery.fn.getNewElementName = function( name, number, namePattern, escape ) {
		var newName = namePattern
			.split( '{name}' ).join( name )
			.split( '{number}' ).join( number );

		//	Escape pattern characters
		if( escape )newName = newName.split('[').join('\\[').split(']').join('\\]').split('.').join('\\.');

		return newName;
	};

	//	Adds a collection
	jQuery.fn.addCollection = function( data, args ) {
		var collectionCount = parseInt( jQuery( args.addButton ).attr( 'collectionCount' ), 10 );
		if( ( collectionCount - args.startCount ) >= args.maxItems )return this;

		if( jQuery.isFunction(args.beforeAdd) )data = args.beforeAdd( data, args ) || data;

		//	Get the template; string = we create it as a DOM element,
		//	Otherwise assume it is already a DOM element.
		var template = null;
		if( typeof args.template === "string" ) {
			var div = document.createElement('DIV');
			div.innerHTML = args.template;
			template = div;
		} else {
			template = jQuery( args.template ).clone( true );
		}

		jQtpl = jQuery( template );
		jQtpl.removeAttr( 'id' );
		jQtpl.addClass( args.target.id + '_collection' );
		jQtpl.css( 'display', '' );

		//	Change the names, label 'for' attribues and add new IDs
		jQtpl.find('*').each( function( index, element ) {
			var jEle = jQuery( element );
			//	Check the element type
			var elementType = jEle.attr( 'type' );
			var name = jEle.attr( 'name' );
			var newName = jQuery.fn.getNewElementName( name, collectionCount, args.namePattern );

			//	Create the element as a string as IE doesn't allow changing names or IDs on radio/checkbox once inserted
			//	into the DOM. See: http://webbugtrack.blogspot.com/2007/08/bug-242-setattribute-doesnt-always-work.html
			//	TODO: detect this behaviour instead of detecting MSIE
			if( ( elementType == 'radio' || elementType == 'checkbox' ) && jQuery.browser.msie ) {

				var eleStr = '<input type="' + jEle.attr('type') + '" ' +
					'id="' + newName + '" ' +
					'name="' + newName + '" ' +
					'originalName="' + name + '" ' +
                    'index="' + collectionCount + '" ' + //-- Added by sina
					'value="' + jEle.attr('value') + '" ' +
					'defaultChecked="defaultChecked" ' +
					'/>';

				var myNewField = document.createElement( eleStr );
				jEle.replaceWith( myNewField );
			}

			//	If we have a name, set it (as the ID as well), otherwise remove ID, so we don't get duplicates
			if (name){
                jEle.attr( 'name', newName ).attr( 'id', newName ).attr( 'originalName', name ).attr( 'index', collectionCount);
                if (elementType != 'hidden') {
                    if (jEle.attr('class').indexOf('calendar') != -1) {
                        jEle.blur(function ()
                        {
                            var stateCtrl = document.forms[0].elements[args.collectionName + '[' + collectionCount + '].state'];
                            if (stateCtrl.value != '1') //-- If not insert mode change state to update mode
                                stateCtrl.value = '2';
                        });
                    } else {
                        jEle.change(function ()
                        {
                            var stateCtrl = document.forms[0].elements[args.collectionName + '[' + collectionCount + '].state'];
                            if (stateCtrl.value != '1') //-- If not insert mode change state to update mode
                                stateCtrl.value = '2';
                        });
                    }
                }

                if (args.onChange != '')
                    jEle.change(args.onChange);
            }

			else jEle.removeAttr( 'id' );

			//	Set the labels 'for' attribute
			var forAttr = jEle.attr( 'for' );
			if( forAttr ) {
				var newFor = jQuery.fn.getNewElementName( forAttr, collectionCount, args.namePattern );
				jEle.attr( 'for', newFor ).attr( 'originalName', forAttr );
			}
		} );

		//	Append the collection to the target DOM node
		jQuery( args.target ).append( template );

		//	Set the values. We do this here to avoid IE issues with values for checkboxes and radio sets
		//	(hence the convoluted way to get the elements)
		//	See: http://webbugtrack.blogspot.com/2007/11/bug-299-setattribute-checked-does-not.html
		jQtpl.find('*').each( function( index, element ) {
			var jEle = jQuery( element );
			//	Check the element type
			var elementType = jEle.attr('type');

			var originalName = jEle.attr( 'originalName' );
			if( originalName ) {
				var value = data[originalName];
				if( value ) {
					//	Set the value
					if( elementType == 'radio' ) {
						if( jEle.val() == value ) {
							jEle.attr( 'checked', true );
							if( jQuery.browser.msie ) {     //	IE workaround
									jEle.attr( 'defaultChecked', 'defaultChecked' );
							}
						}
					} else if ( elementType == 'checkbox' ) {
						jEle.attr( 'checked', true );
					} else {
						jEle.val( value );
					}
				}
			}
		} );

		//	Bind the delete button
		//	This pattern needs square brackets and dots escaped.
		var delButtonPattern = jQuery.fn.getNewElementName( args.deleteButtonName, collectionCount, args.namePattern, true );
		var delButton = jQtpl.find( '#' + delButtonPattern );

		if( delButton.length > 0 ) {
			delButton.click( function() { args.deleteFunction( template, args, data, this ); } );
			//	Add the data as an expando on the delete button (for use in remove function)
			jQuery.data( delButton.get(0), 'dataObj', data );
		}

		//	Set the counter on the add button
		jQuery( args.addButton ).attr( 'collectionCount', collectionCount + 1 );

		if( jQuery.isFunction( args.afterAdd ) )args.afterAdd( data, args );

		//	Add custom bindings here - we pass the event and the collection container (template)
		var bindFunc = function( func ) {
			return function(e) {
				return func( e, template );
			}
		};

		for( var name in args.bindings ) { if( args.bindings.hasOwnProperty( name ) ) {
			var elementName = jQuery.fn.getNewElementName( name, collectionCount, args.namePattern, true );
			var bind = args.bindings[name];
			jQtpl.find( '#' + elementName ).bind( bind.event, bindFunc(bind.func) );
		} };

		return this;
	};

	//	Utility func to swap values between two input fields
	jQuery.fn.swapFieldValues = function( field1, field2 ) {
		var val1 = jQuery( field1 ).val();
		var val2 = jQuery( field2 ).val();
		jQuery( field1 ).val( val2 );
		jQuery( field2 ).val( val1 );
	};


	//	Moves a collection up optionally freezing cartain field values; returns true if successful
	jQuery.fn.moveUpCollection = function( container, freezeFieldValues ) {
		var prevContainer = jQuery( container ).prev();
		if( ! prevContainer ) {
			return false;
		} else {
			jQuery(container).after(prevContainer);
			if( freezeFieldValues ) {
				if( typeof freezeFieldValues !== 'Array' )freezeFieldValues = [freezeFieldValues];
				for( var i = 0; i < freezeFieldValues.length; i++ ) {
					//	Match the fields here using the 'originalname' expando
					var query = "[originalname=" + freezeFieldValues[i] +"]";
					jQuery.fn.swapFieldValues( jQuery( container ).find( query ), jQuery( prevContainer ).find( query ) );
				}
			}
			return true;
		}
	};

	//	Moves a collection down optionally freezing cartain field values; returns true if successful
	jQuery.fn.moveDownCollection = function( container, freezeFieldValues ) {
		var nextContainer = jQuery( container ).next();
		if( ! nextContainer ) {
			return false;
		} else {
			jQuery(nextContainer).after(container);
			if( freezeFieldValues ) {
				if( typeof freezeFieldValues !== 'Array' )freezeFieldValues = [freezeFieldValues];
				for( var i = 0; i < freezeFieldValues.length; i++ ) {
					//	Match the fields here using the 'originalname' expando
					var query = "[originalname=" + freezeFieldValues[i] +"]";
					jQuery.fn.swapFieldValues( jQuery( container ).find( query ), jQuery( nextContainer ).find( query ) );
				}
			}
			return true;
		}
	};



	//	Removes a collection
	jQuery.fn.removeCollection = function( element, args, data ) {
		var collectionCount = parseInt( jQuery( args.addButton ).attr( 'collectionCount' ), 10 );
		var addBlankAfterRemoval = ( ( collectionCount - args.startCount ) <= args.minItems );
		var result = null;
		var wasRemoved = false;
		if( jQuery.isFunction( args.beforeRemove ) )result = args.beforeRemove( data, args );

		//	Allow the beforeRemove function to return false, if we don't want to remove an item.
		if( result !== false ) {
			//	remove the collection and update counter
			jQuery( element ).remove();
			wasRemoved = true;
			jQuery( args.addButton ).attr( 'collectionCount', collectionCount - 1 );

			//	Create a new blank item if necessary
			if( addBlankAfterRemoval )jQuery.fn.addCollection( {}, args );

			//	Adjust each collection's ID and name
			jQuery( args.target ).find('.' + args.target.id + '_collection').each( function( index, element ) {
				var count = parseInt( args.startCount, 10 ) + index;
				jQuery( element ).find('*').each( function( eIndex, eElement ) {
					var jEle = jQuery( eElement );
					var newName = jQuery.fn.getNewElementName( jEle.attr( 'originalName' ), count, args.namePattern );
					if( jEle.attr( 'name' ) )jEle.attr( 'name', newName ).attr( 'id', newName ).attr( 'index', count );
					if( jEle.attr( 'for' )  )jEle.attr( 'for', newName );
				} );
			} );

			//	Adjust the container IDs (this MUST be in a seperate loop to the above)
			jQuery( args.target ).find('.' + args.target.id + '_collection').each( function( index, element ) {
				var count = parseInt( args.startCount, 10 ) + index;
				var id = jQuery( element ).attr( 'id' );
				if( id ) {
					var newId = id.substring( 0, element.id.lastIndexOf( '_' ) + 1 ) + count;
					jQuery( element ).attr( 'id', newId );
				}
			} );
		}

		//	Run the afterRemove function - we indicate if the item was actually removed.
		if( jQuery.isFunction( args.afterRemove ) )args.afterRemove( data, args, wasRemoved );

		return this;
	};

})(jQuery);