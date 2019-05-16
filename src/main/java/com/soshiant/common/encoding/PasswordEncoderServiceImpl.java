package com.soshiant.common.encoding;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class PasswordEncoderServiceImpl implements PasswordEncoder {

    private boolean validNullRawPass = false;

    //    @Override
    public String encodePassword(String rawPass, Object salt) throws DataAccessException {
        String digest;

        digest = MessageDigestUtils.sha2AndMd5DigestToHexString(rawPass);
        if (digest == null)
            throw new IllegalStateException("rawPass is Invalid");

        return digest;
    }

    //    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException {

        String pass1 = "" + encPass;
        String pass2 = encodePassword(rawPass, salt);

        return pass1.equals(pass2);
    }

    public void setValidNullRawPass(boolean validNullRawPass) {
        this.validNullRawPass = validNullRawPass;
    }

}
