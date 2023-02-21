KegelApp von KC Volle Pumpe


***************
Funktionsweise:

App liest Daten aus https://github.com/sebuech02/kegeliege_rangliste/blob/main/rangliste.xml und https://github.com/sebuech02/kegliege_xml/blob/main/data.xml aus und stellt sie dann dar.

Daten werden in der MainActivity bei onCreate eingelesen, definierend sind dabei die tags, und in die Klassen spiel_liste und ranglisten_daten durch set Methoden gespeichert. 
Datenformat beruht auf einer anaeinanderreihung von ArrayListen.
In den verschiedenen Kategorien werden die Daten dann mit get Methoden ausgelesen und angezeigt.

Bier wird über eine SQL Datenbank bestellt, indem verschiedeneQuaries gesendet werden. (SQL injection leider möglich)

Spiele können in der App erstellt und an mich zur überprüfung gesendet werden und dann im XML Format an die Liste gefügt werden (mit kleiner Sicherheitslücke)
********************************
Geplante/dran gedachte Features:

  - Einstellungen mit option die Sortierung zu ändern
  - alle Daten auf SQL-server packen
  - Animationen und visuals
  - Spiele online sync mit erstellen
  - (Server fehlt)
*****************
Fertige Features:
  
  - Notifications work, speil erstellen sendet Token für persöhnliche Nachricht
  - online Strichliste für Bier bestellen, damit man dem Bierbesteller per knopfdruck klar machen kann was man möchte (Server tot).
  - Kegelspiel mit gameengine (Work in Progress)
  - spiele einlesen aus xml (web-basiert) + rangliste einlesen 
    - hinzufügen von Spielen in der app. (benötigt noch manuelle Freigabe)
  - Strichliste für die Pumpne in der App bearbeiten und ggf. teilen
