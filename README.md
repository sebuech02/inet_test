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

  - alle Daten auf SQL-server packen (Wegen Performance lieber als Socket)
  - Animationen und visuals 
  - Spiele online sync mit erstellen (evtl. mangels moderation keine gute idee)
  - (Server fehlt/müsste aufgesetzt werden)
  - vielleicht helfer für Hausnummer oder strichliste (Mehr Helfer-klassen)
*****************
Fertige Features:
  
  - Notifications work, Spiel erstellen sendet Token für persöhnliche Nachricht
  - online Strichliste für Bier bestellen, damit man dem Bierbesteller per knopfdruck klar machen kann was man möchte (Server tot und ausgebaut).
  - Kegelspiel mit gameengine (Work in Progress), flappy Carlos geht und andere Spiele sind in arbeit
  - spiele einlesen aus xml (web-basiert) + rangliste einlesen 
    - hinzufügen von Spielen in der app. (benötigt noch manuelle Freigabe)
  - Strichliste für die Pumpen in der App bearbeiten und ggf. teilen
  - Einstellungen mit option die Sortierung zu ändern und auch die Vibration ein und auszuschalten
  - spielehelfer, die verschiedene Funktionen haben und haptisches Feedback haben
      - Zufallszahl von bis
      - Karteziehen mehrere Decks
      - würfeln (belibig viele)
      - 5er spiel erweitert als helfer
      - zufallsauswahl
      - punkte zählen
