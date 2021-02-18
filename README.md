# TicketMasterApp

## Scaletta
1. [Introduzione](#Introduzione)
2. [UML](#UML)
3. [Rotte](#Rotte)
4. [Test](#Test)
5. [Autori](#Autori)

## Introduzione
La nostra applicazione TicketMasterApp permette all'utente di conoscere i principali eventi che si svolgono in alcune provincie del Canada, questo grazie all'utilizzo dell'API TicketMaster.
L'applicazione fornisce all'utente la possibilità di ottenere delle informazioni riguardanti i promoter e gli eventi che essi sponsorizzano nelle varie provincie, attraverso l'utilizzo di statistiche e  di filtri, che facilitano la ricerca dei dati desiderati.  
Nell'uso di questa applicazione si ricorre quindi, all'utilizzo di alcune rotte che consentono la visualizzazione di statistiche e la loro filtrazione e al fine di agevolare l'utente, si è messa a disposizione una rotta che ritorna le informazioni relative hai promoter da utilizzare, in alcuni casi, per effettuare delle chiamate.
E' possibile infatti visualizzare statistiche relative agli eventi di ogni provincia e per uno o più promoter.
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

###### Package utils
![Diagramma delle classi utils stats filter](https://user-images.githubusercontent.com/77582844/108414250-4b286c80-722c-11eb-90ae-120a1d44f395.PNG)

###### package controller
![Diagramma delle classi package controller](https://user-images.githubusercontent.com/77582844/108414550-a9ede600-722c-11eb-9084-0329a68bff24.PNG)

#### Diagramma delle sequenze
###### /Promoter
![Diagramma delle sequenze Promoter](https://user-images.githubusercontent.com/77582844/107984222-c1786500-6fc7-11eb-9a53-2391aa6521f3.PNG)

###### /StatsRegion
![Diagramma delle sequenze statsReg](https://user-images.githubusercontent.com/77582844/108043947-f88b5c80-7041-11eb-8a6f-85c11e561fb1.PNG)

###### /StatsPromoter
![Diagramma delle sequenze statsPromoter](https://user-images.githubusercontent.com/77582844/108044217-47d18d00-7042-11eb-86bc-cb22962121e5.PNG)

## Rotte
L'utente può effettuare le richieste tramite Postman al seguente indirizzo
```
localhost:8080
```
Le rotte disponibili sono le seguenti:

N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
[1](#1) | ` GET ` | `/Promoter?stateCode=MB` | *restituisce un JSONArray composto da molti JSONObject al cui interno viene specificato: 1. ID; 2. nome; 3. descrizione del promoter. *
[2](#2) | ` GET ` | `/statsReg` | *restituisce un JSONArray con all'interno molti JSONObject che forniscono statistiche relative ad ogni provincia, in particolare ritorna: 1. Il numero di promoter che sponsorizzano eventi in quello stato suddivisi per genere di evento; 2. il num totale di promoter che sponsorizzano eventi in quello stato; 3. il minimo,massimo e  la media degli eventi mensili che si svolgono in quella data regione, nell'anno corrente.*
[3](#3) | ` POST ` | `/statsProm` | *restituisce un JSONAarry che contiene dei JSONObject che forniscono statistiche relative ai promoter, tra cui: 1.il numero totale di eventi che sponsorizzano; 2. il numero totale di eventi per genere che sponsorizza il promoter; 3. il numero di provincie in cui il promoter sponsorizza l'evento.*
[4](#4) | ` POST ` | `/filterstats` | * restituisce un JSONArray con all'interno dei JSONObject, che contengono informazioni sulle statistiche, ma filtrate attraverso dei parametri specificati dall'utente, in più restituiscono il numero massimo, minimo e la media di eventi per un periodo scelto dall'utente*

### 1. GET /Promoter
Questa rotta fornisce come suggerimento per l'utente una lista di promoter presenti in una regione.
Di default viene visualizzata una lista di promoter della provincia Manitoba, ma è possibile ottenere la lista anche per altre regioni, grazie all'utilizzo del parametro **stateCode**

**NOTA** Come valore del parametro è necessario specificare il codice postale della regione in questione, esempio:                     
**Alberta** ==>(**stateCode**=**AB**)                      
**Quebec** ==> (**stateCode**=**QC**)                          
**Manitoba** ==> (**stateCode**=**MB**)                                     
**Saskatchewan** ==> (**stateCode**=**SK**)                                                            
**Nuovo Brunswick** ==> (**stateCode**=**NB**)


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

#### Eccezioni che può generare la chiamata

 - Nel caso in cui l'utente dovesse inserire una regione tale per cui non ci sono promoter validi o specificati, viene lanciata l'eccezione ***NoPromoterExcepton*** che stampa il seguente messaggio:
 
 ```
In questa regione non è specificato nessun promoter, prova altre regioni consentite...
     
```

 - Se l'utente inserisce uno stateCode errato o non presenti tra le provincie analizzate, viene eseguita l'eccezione ***WrongStateCodeException***, che riporta il seguente messaggio:

```
Gli stateCode consentiti sono: 
AB(Alberta),
QC(Quebec),
MB(Manitoba),
SK(Saskatchewanc),
NB(Nuovo Brunswick)

```

 


 

### 2. GET /statsReg
Restituisce le statistiche per ogni regione.
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
2. **"Eventi mensili"** : ci restituisce il numero di eventi mensili che si svolgono in un determinato stato, nell'anno corrente.
   In particolare "media" non è altro che la media totale degli eventi che si svolgono in un mese, "min" indica il numero minimo di eventi che si ha in un mese, "max" il    numero massimo di eventi mensili
3. **"Tot_Prom_Genere"** : numero totale di promoter per il genere di evento che sponsorizzano in quella provincia. Ad esempio, "Music" viene sponsorizzata da undici promoter e così via con gli altri;
4. **"Tot_Prom"** : restituisce il numero totale di promoter che sponsorizzano eventi in quella regione.

#### Risultato chiamata su Postman
![response rotta Stats reg](https://user-images.githubusercontent.com/77582844/108222568-328b5a00-7139-11eb-882e-e800ce67f54a.png)





### 3. POST /statsProm
Questa è una chiamata di tipo **POST**, che restituisce statistiche sui promoter scelti dall'utente, passando come **parametri l'ID** relativo al promoter.
#### Modello del Body
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

I risultato della richiesta stampa:

1.**ID_Promoter**: l'ID che identifica il promoter.                                                                                         
2.**Tot_Eventi_Genere**: Restituisce il numero totali di eventi suddivisi per genere, che vengono sponsorizzati dal promoter                                              
3.**Tot_Stati_Evento**: Numero di stati in cui il promoter sponsorizza un determinato evento                                                                   
4.**Tot_Eventi**: numero totale di eventi sponsorizzati dal promoter                                                                        

#### Risultato chiamata su postman
![Rotta statsProm](https://user-images.githubusercontent.com/77582844/108227543-4ab1a800-713e-11eb-9cae-b064e829e0c0.png)

#### Eccezioni che può generare la chiamata

- Se l'utente inserisce un ID errato, viene lanciata l'eccezione ***WrongIDException*** che restituisce il seguente messaggio di errore:

 ```
L'ID inserito è errato o non esiste, si prega di utilizzare la rotta\"localhost:8080/promoter\" per ottenre una lista di ID promoter validi
     
```
 - Nel caso in cui l'utente si dimentica di compilare un campo del body, parte il metodo ***EmptyIDException*** che stampa a video questo messaggio:

```
E' necessario riempire tutti i campi del corpo per un corretto funzionamento...

```
 - Se l'utente dovesse inserire un body vuoto, viene fatta partire l'eccezione ***NoBodyException***, con il seguente messaggio:

```
E' richiesto un body di questo tipo:
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


### 4. POST /filterstats
Restituisce le statistiche relative alla regione e ai promoter filtrate per uno o più province, uno o più generi e un periodo di tempo, per il calcolo del numero minimo, massimo e medio degli eventi, per quel periodo.
L'utente può scegliere di effettuare il calcolo per un periodo trimestrale o semestrale, inserendo il numero 3 o 6
In più l'utente deve selezionare su quale statistica vuole effettuare i seguenti filtri

#### Modello del Body

```
{
    "regione": [
        {
            "stateCode": "MB"
        },
        {
            "stateCode": "AB"
        },
        {
            "stateCode": "QC"
        }
    ],
    "genere": [
        {
            "name": "Music"
        },
        {
            "name": "Sport"
        }
    ],
    "periodo": 3,
        
   
    "stats": "statsReg"
}
```
Parametri da inserire per poter filtrare le statistiche sono:
 -**stateCode**:  il codice postale di una o più province, si puo scegliere tra:
 ```
- AB(Alberta),
- QC(Quebec),
- MB(Manitoba),
- SK(Saskatchewanc),
- NB(Nuovo Brunswick)
```
 
 -**name**: il nome della tipologia del genere, è possibile scegliere tra:
 ```
- Music,
- Sport,
- Film,
- Art & Theatre,
- Altro
```

Se l'utente non dovesse inserire i JSONObject relativi ai campi **stateCode** e **genere**, allora viene restituito solo la statistica per il periodo scelto

-**periodo**: si può scegliere tra un periodo trimestrale(3) e uno semestrale(6)

-**stats**: indica per quale statistica si vuole effettuare il filtraggio, quindi scegliere di inserire uno tra i due:
```
- statsReg
- statsProm
```

#### Eccezioni che può generare la chiamata

- Se l'utente dovesse inserire un body vuoto, viene fatta partire l'eccezione ***NoBodyFilterException***, con il seguente messaggio:
```
E' richiesto un body di questo tipo:\n"
			{
    "regione":[
        {
          "stateCode": "AB"
        },
        {
            "stateCode":"QC"
        }
    ],
    "genere":[
        {
            "name": "Music"
        },
        {
            "name": "Sport"
        }
    ],
    "periodo": 3,
    "param": "statsreg"
}

```
- Nel caso in cui l'utente devesso inserire una tipologia di genere non consentita, o incorretta, viene lanciata l'eccezione ***WrongParamException***, con il seguente messaggio:

```
I parametri inseriti non sono tra quelli consentiti o sono errati, perfavore scegliere tra questi:
Music
Sport
Art & Theatre
Film
Altro
```
-Nel caso in cui l'utente inserisce un periodo diverso da 3 o 6, viene fatta partire l'eccezione ***WrongPeriodException***
```
E' possibile calcolare il periodo solo trimestralmente(£) o semestralmente(6), inserire uno di questi due valori

```
- Nel caso invece lutente inserisse degli stateCode errati o non presenti tra le provincie analizzate, viene eseguita l'eccezione ***WrongStateCodeException***, che riporta il seguente messaggio:

```
Gli stateCode consentiti sono: 
AB(Alberta),
QC(Quebec),
MB(Manitoba),
SK(Saskatchewanc),
NB(Nuovo Brunswick)

```
## Test

>Nel programma vengono anche effettuati alcuni test:
>Test relativi al package service
                             
-**Test 1**
Verifica  se il download dei dati ed inserimento di questi in delle strutture dati, da esito nullo

-**Test 2**
Verifica che l'eccezione WrongStateCodeException sia lanciata correttamente

>Test relativi alle statistiche, package stats

-**Test 3**
Verifica che l'eccezione EmptyIDException venga lanciata correttamente





## Autori
Il progetto è stato realizzato da:
1. Elisa Pace
2. Marco Vassallo

entrambi hanno contribuito al 50% nella realizzazione di questo progetto
