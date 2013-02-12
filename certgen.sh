#!/bin/csh
rm ~/.keystore
keytool -genkey -keyalg "RSA" -storepass rootroot -validity 365 -alias SSLCertificate
keytool -list -storepass rootroot
keytool -export -alias SSLCertificate -storepass rootroot -file server.cer
