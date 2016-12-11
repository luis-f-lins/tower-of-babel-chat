# Chat and send translated messages

## Install 
*(needs [GNU/awk](https://www.gnu.org/software/gawk/), Java [JDK and JRE](http://www.oracle.com/technetwork/pt/java/javase/downloads/index.html) to work)*

*(if you're on Linux or Mac OS, that's all. if you're on Windows, you will also need [Bash for Windows](https://msdn.microsoft.com/pt-br/commandline/wsl/faq) - Windows 10, only - or [Cygwin](https://www.cygwin.com), for previous versions of Windows)*

`bash build.sh`

## Run

Inside folder **bin**, run Client with java. Args are: 

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
