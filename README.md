# Chat and send translated messages

## Install (needs [GNU/awk](https://www.gnu.org/software/gawk/) to work)

`bash build.sh`

## Run

Inside folder **bin** run Client with java, args are: **from_username**, **to_username**, **to_ip**, **send_port**, **receive_port**

-from means you

-to means who you are talking to

-to_host can be a local ip address if in LAN

-for local chats exchange orders of send_port and receive_port

**Example:**

`java Client me you localhost 3000 3001`


`java Client you me localhost 3001 3000`

## License

GPL-3.0
