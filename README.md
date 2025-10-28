Das Projekt ausführen

Option 1: Mit dem Run-Skript in der Shell
     ./run.sh

Option 2: Manuelle Docker-Compose-Befehle

1.	Zum Docker-Directory navigieren:
     cd Docker

2.	Alle Services starten:
     docker-compose up –build

Um im detached mode auszuführen:
     docker-compose up –build -d (Ich bevorzuge den detached mode.)

Service-Endpunkte
	•	bestandsysteme: http://localhost:8081
	•	middleware-textvorsystem: http://localhost:8082
	•	tesys: http://localhost:8084
	•	ActiveMQ Management Console: http://localhost:8161 (admin/admin)

Services stoppen
docker-compose down

Um auch Volumes zu entfernen:
docker-compose down -v


Nachdem in der Anwendung der Befehl docker-compose up –build ausgeführt und alle Services gestartet wurden:

Wenn über http://localhost:8081/trigger ein Content per POST gesendet wird, erhält dieser Content eine ID und einen Timestamp. Der Content wird zunächst vom Bestandsysteme-Modul empfangen und über RestTemplate an das Middleware-Modul weitergeleitet.

Im Middleware-Modul wird der empfangene Content in eine ActiveMQ-Queue eingefügt und gleichzeitig erneut per RestTemplate an TeSyS weitergeleitet. Auf diese Weise habe ich eine Architektur aufgebaut, die der Modellierung im ersten Teil ähnelt.

Über http://localhost:8081/trigger/get können die geposteten Contents zusammen mit ihren ID- und Timestamp-Informationen angezeigt werden.

Mit http://localhost:8081/trigger/delete/{id} kann ein Content, dessen ID bekannt ist, gelöscht werden.
