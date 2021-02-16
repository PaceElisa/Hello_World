# TicketMasterApp

## Scaletta
1. [Introduzione](#Introduzione)
2. [UML](#UML)
3. [Rotte](#Rotte)
4. [Test](#Test)
5. [Autori](#Autori)

## Introduzione
La nostra applicazione permette a chi la consulta di sapere i principali eventi che si svolgeranno in alcune provincie del Canada tramite l'utilizzo dell'API TicketMaster.
Inoltre per agevolare l'utente nell'uso di questa applicazione si ricorre all'utilizzo di alcune rotte che consentono la visualizzazione di statistiche e la loro filtrazione.
E' possibile infatti visualizzare statistiche relative agli eventi di una determinata provincia e per uno o più promoter.
L'utente può scegliere tra gli eventi che si svolgeranno nelle seguenti provincie:
1. Alberta
2. Saskatchewan
3. Manitoba
4. Quebec
5. Nuovo Brunswick

## UML
#### Diagramma dei casi d'uso
![UML Diagramma dei casi d'uso](https://user-images.githubusercontent.com/77582844/107976917-989da300-6fba-11eb-931b-a115e03b0430.PNG)

#### Diagramma delle sequenze
###### /Promoter
![Diagramma delle sequenze Promoter](https://user-images.githubusercontent.com/77582844/107984222-c1786500-6fc7-11eb-9a53-2391aa6521f3.PNG)

###### /StatsRegion
![Diagramma delle sequenze statsReg](https://user-images.githubusercontent.com/77582844/108043947-f88b5c80-7041-11eb-8a6f-85c11e561fb1.PNG)

###### /StatsPromoter
![Diagramma delle sequenze statsPromoter](https://user-images.githubusercontent.com/77582844/108044217-47d18d00-7042-11eb-86bc-cb22962121e5.PNG)

## Rotte
N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
[1](#1) | ` GET ` | `/Promoter` | *restituisce un JSONArray composto da molti JSONObject al cui interno viene specificato: 1. ID; 2. nome; 3. descrizione del promoter. *
[2](#2) | ` GET ` | `/statsReg` | *restituisce un JSONArray con all'interno molti JSONObject che forniscono: 1. le statistiche della regione che ospita l'evento; 2. il num totale di promoter che sponsorizzano tale evento; 3. il numero totale di eventi mensili in quella data regione.*
[3](#3) | ` GET ` | `/statsProm` | *restituisce un JSONAarry che contiene dei JSONObject che contengono: 1.il numero totale di eventi; 2. il numero totale di eventi per genere che sponsorizza il promoter; 3. il numero di stati in cui il promoter sponsorizza l'evento.*

## Autori
Il progetto è stato realizzato da:
1. Elisa Pace
2. Marco Vassallo
