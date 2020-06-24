**Aufgabenstellung: Politische Reden**

Szenario: Implementierung eines Features in Java oder Scala mit anschließender Code-Review und produktivem Deployment.

Ziel: Verarbeitung von Statistiken über politische Reden.

Input: CSV-Dateien (UTF-8 Encoding), die folgendem Schema entsprechen: 

Redner, Thema, Datum, Wörter

Es soll ein HTTP-Server mit maven oder sbt gestartet werden können, der 1 oder mehrere URLS als Query-Parameter unter der Route GET /evaluation?url1=url1&url2=url2 entgegennimmt. Die an diesen URLs befindlichen CSV-Dateien werden ausgewertet und bei validem Input folgende Fragen beantwortet: 

1. Welcher Politiker hielt im Jahr 2013 die meisten Reden?

2. Welcher Politiker hielt die meisten Reden zum Thema ”Innere Sicherheit”?

3. Welcher Politiker sprach insgesamt die wenigsten Wörter?

Ausgabe: JSON in folgendem Format.

{
”mostSpeeches”: String | null,
“mostSecurity”: String | null,
“leastWordy”: String | null
}

Wenn für eine Frage keine, oder keine eindeutige Antwort möglich ist, soll dieses Feld mit null gefüllt werden.

Beispiel:

CSV-Inhalt:

Redner, Thema, Datum, Wörter

Alexander Abel, Bildungspolitik, 2012-10-30, 5310

Bernhard Belling, Kohlesubventionen, 2012-11-05, 1210

Caesare Collins, Kohlesubventionen, 2012-11-06, 1119

Alexander Abel, Innere Sicherheit, 2012-12-11, 911

Response:

Status: 200

Body: {
”mostSpeeches”: null,
“mostSecurity”: "Alexander Abel",
“leastWordy”: "Caesare Collins"
}

Bewertung findet auf Basis einer fiktiven Code-Review durch Teammitglieder und den gleichen Qualitätsansprüchen an Code-Qualität, Testabdeckung, Verständlichkeit wie für Produktionscode statt. Der Code sollte simpel und zielgerichtet sein. 

Have fun ! :-)
