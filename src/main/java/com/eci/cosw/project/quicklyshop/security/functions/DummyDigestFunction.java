package com.eci.cosw.project.quicklyshop.security.functions;

public class DummyDigestFunction implements DigestFunction {


    @Override
    public boolean matches(CharSequence rawPassword, CharSequence encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }

    @Override
    public CharSequence encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }
}
