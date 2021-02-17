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

#### Diagramma delle classi
###### Package model
![Diagramma delle classi package model](https://user-images.githubusercontent.com/77582844/108220785-65cce980-7137-11eb-9bdf-795e65d68a49.PNG)

###### Package service
![Diagramma delle classi package service](https://user-images.githubusercontent.com/77582844/108220942-8d23b680-7137-11eb-84e4-38e09c9a47c4.PNG)

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

### 1. GET /Promoter
#### Modello
```
{
      "id": "653"
       "nome": "LIVE NATION MUSIC"
      "descrizione": "LIVE NATION MUSIC / NTL / USA"
    } 
```
dove:
1. **"id"** : id del promoter che sponsorizza l'evento;
2. **"nome"** : nome del promoter;
3. **"descrizione"** : restituisce alcune informazioni riguardo al promoter.

#### Risultato chiamata su postman
![Rotta Promoter (1)](https://user-images.githubusercontent.com/77582844/108105317-c4895900-708c-11eb-99e1-fb7b2ef02cd4.png)

### 2. GET /statsReg
#### Modello
    {
        "Regione": "Alberta",
        "Eventi_Mensili": {
            "min": 13,
            "max": 45,
            "media": 29
        },
        "Tot_Prom_Genere": {
            "Music": 11,
            "Sport": 5,
            "Art & Theatre": 7,
            "Film": 2,
            "Altro": 6

         },
        "Tot_Prom": 31
    }
dove :
1. **"Regione"** : restituisce il nome della regione in cui si svolgono gli eventi;
2. **"Eventi mensili"** : ci restituisce il numero di eventi che si svolgeranno in un determinato periodo ti tempo a scelta dell'utente.
   In particolare "media" non è altro che la media totale degli eventi che si possono svolgere in un mese. "min" indica il numero minimo di eventi che si ha in un mese. "max" il    numero massimo di eventi
3. **"Tot_Prom_Genere"** : individua i vari generi degli eventi che si tengono in una data regione e di questi ne fa una conta. Ad esempio, "Music" ha undici eventi di questo genere, e così via con gli altri;
4. **"Tot_Prom"** : restituisce il numero totale di eventi di quella regione.

#### Risultato chiamata su postman
![response rotta Stats reg](https://user-images.githubusercontent.com/77582844/108222568-328b5a00-7139-11eb-882e-e800ce67f54a.png)

### 3. GET /statsProm
#### Modello
```
{
       "promoter": [
        {
            "ID": "850"
        },
        {
            "ID": "653"
        },
        {
            "ID": "494"
        }
    ]
}
```
in cui gli ID che sono presenti nel JSON sopra riportati si riferisco ai promoter che organizzano un dato evento
## Autori
Il progetto è stato realizzato da:
1. Elisa Pace
2. Marco Vassallo

entrambi hanno contribuito al 50% nella realizzazione di questo progetto
