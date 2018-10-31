package com.eci.cosw.project.quicklyshop.digestfunctions;

public class RawDigestFunction implements DigestFunction {


    @Override
    public boolean matches(CharSequence rawPassword, CharSequence encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }

    @Override
    public CharSequence encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }
}
