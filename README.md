# Cake-VPN
Android VPN app based on OpenVPN library.

</br>
<img height='500' width ='300' src="https://i.imgur.com/5GV1Y2L.gif" />
</br></br>

## Note

I have used free OpenVPN configuration file from this site https://www.vpngate.net/en/ </br>
I am not sure how long it will work with the free ovpn file. It's better to update ovpn files with your own ovpn.
</br></br>

### Instruction to update server list:
1. Replace your .ovpn file with <b> assets/</b> directory .ovpn file
2. Now go to MainActivity.class and find the "getServerList()" method there you have to update server information.
3. At Last go to SharedPreference.class and find the "getServer()" method there you have update default server information.
</br> </br>

## License
**Free Software!**

This project and the uses VPN library "ICS OpenVPN" both are under GPLv2 License.

> Make sure you understand the licenses of the code. OpenVPN for Android is GPL licensed.

- see the [License File](LICENSE) for more details.
