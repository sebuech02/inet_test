KegelApp von KC Volle Pumpe


***************
Funktionsweise:

App liest Daten aus https://github.com/sebuech02/kegeliege_rangliste/blob/main/rangliste.xml und https://github.com/sebuech02/kegliege_xml/blob/main/data.xml aus und stellt sie dann dar.

Daten werden in der MainActivity bei onCreate eingelesen, definierend sind dabei die tags, und in die Klassen spiel_liste und ranglisten_daten durch set Methoden gespeichert. 
Datenformat beruht auf einer anaeinanderreihung von ArrayListen.
In den verschiedenen Kategorien werden die Daten dann mit get Methoden ausgelesen und angezeigt.
********************************
Geplante/dran gedachte Features:

  - hinzufügen von Spielen in der app. (online Sync)
  - alle Daten auf SQL-server packen
  - Animationen und visuals
*****************
Fertige Features:

  - online Strichliste für Bier bestellen, damit man dem Bierbesteller per knopfdruck klar machen kann was man möchte.
