class LOG {
    public static void debug(String message) {
        if (Config.DEBUG)
            System.out.println(message);
    }
}
