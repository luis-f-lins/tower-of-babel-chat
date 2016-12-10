# Chat and send translated messages

## Install (needs [translate-shell](https://github.com/soimort/translate-shell) and [GNU/awk](https://www.gnu.org/software/gawk/) to work)

`bash download_translator.sh`

`mkdir -p bin`

`javac src/Client.java src/GUI.java src/Packet.java src/Receiver.java src/Translator.java src/Log.java src/Config.java -d bin`

`cd bin`

## Run

Inside folder bin run Client with java, args are: **from_username**, **to_username**, **to_ip**, **send_port**, **receive_port**

-from means you

-to means who you are talking to

-to_host can be a local ip address if in LAN

-for local chats exchange orders of send_port and receive_port

**Example:**

`java Client me you localhost 3000 3001`


`java Client you me localhost 3001 3000`

## License

GPL-v3.0
