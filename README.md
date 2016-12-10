# Chat and send translated messages

## Install 
*(needs [GNU/awk](https://www.gnu.org/software/gawk/) to work)*

`bash build.sh`

## Run

Inside folder **bin** run Client with java, args are: 

&nbsp;&nbsp;&nbsp;&nbsp;**from_username**, 

&nbsp;&nbsp;&nbsp;&nbsp;**to_username**, 

&nbsp;&nbsp;&nbsp;&nbsp;**to_ip**,

&nbsp;&nbsp;&nbsp;&nbsp;**send_port**, 

&nbsp;&nbsp;&nbsp;&nbsp;**receive_port**

*(needs port forwarding to work outside of LAN)*

**Example:**

`java Client me you localhost 3000 3001`


`java Client you me localhost 3001 3000`

## License

GPL-3.0
