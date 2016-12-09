class Translator {
    public static String go(String message) {
        String to_lang = Translator.parse_to_lang(message);
        message = Translator.clean_message(message);
        return Translator.message_to(message, to_lang);
    }

    static String parse_to_lang(String message) {
        return null;
    }

    static String clean_message(String message) {
        return message;
    }

    static String message_to(String message, String lang_to) {
        return message;
    }
}
