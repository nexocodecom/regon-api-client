package com.bir.client;

import org.springframework.util.StringUtils;

class IdValidator {

    private static final int NIP_LENGTH = 10;
    private static final int REGON_LENGTH_SHORT = 9;
    private static final int REGON_LENGTH_LONG = 14;

    static String normalizeAndValidateNip(String nip) throws Exception {
        String normalized = nip.replaceAll("[^0-9.]", "");
        return validateNip(normalized);
    }


    static String normalizeAndValidateRegon(String regon) throws Exception {
        String normalized = regon.replaceAll("[^0-9.]", "");
        return validateRegon(normalized);
    }


    private static String validateNip(String nip) throws Exception {
        if (StringUtils.isEmpty(nip)) {
            throw new Exception("NIP number is empty.");
        }

        if (nip.length() != NIP_LENGTH) {
            throw new Exception("NIP is invalid. Required length: " + NIP_LENGTH);
        }

        return nip;
    }


    private static String validateRegon(String regon) throws Exception {
        if (StringUtils.isEmpty(regon)) {
            throw new Exception("REGON number is empty.");
        }


        if (regon.length() == REGON_LENGTH_SHORT || regon.length() == REGON_LENGTH_LONG) {
            return regon;
        }
        throw new Exception("REGON is invalid. Required length: " + REGON_LENGTH_SHORT + " or " + REGON_LENGTH_LONG);
    }
}
