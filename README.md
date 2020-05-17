task:
U programskom jeziku Java ostvariti komandno-linijsku aplikaciju naziva Demo koja učitava
popis studenata te ih pri pokretanju sprema u "in memory" bazu te omogućuje postavljanje upita nad njom.

1. Popis studenata zapišite u datoteku CSV formata koja sadrži retke u sljedećem obliku:
"jmbag;ime;prezime;ocjena"
2. Program se treba moći pokrenuti s jednim argumentom, apsolutnim putom do datoteke koja sadrzi popis studenata,
npr: "java Demo /tmp/files/students.csv"
3. Implementacija "in memory" baze je proizvoljna, može biti i obična ArrayList-a
koja će tijekom rada programa sadržavati listu programa nad kojom će se postavljati upiti
4. Program treba nastaviti s radom te omogućiti unos određenog skupa naredbi od korisnika.
Naredbe koje program treba podržati su sljedeće:
	4.1. "create arg1 arg2 arg3 arg4" - stvara novog studenta s danim argumentima te ga sprema u bazu
	4.2. "read arg1" - ispisuje detalje studenta čiji je jmbag jednak danom argumentu arg1
	4.3. "filter-grade arg1 arg2" - ispisuje detalje svih studenata čija je ocjena
	    manja/jednaka/veća od ocjene dane argumentom arg2, argument arg1 može biti jedan od 3 mogućih vrijednosti {"l", "g", "e"},
	    te označava korištenu relaciju (lower, greater ili equal).
	4.4. "filter-name arg1 arg2" - ispisuje ime i prezime svih studenata čije ime
	    počinje s argumentom arg1, argument arg2 je opcionalan te ukoliko je prisutan njegova vrijednost
	    mora biti "-u" ili "-l" te označava da se ime i prezime ispisuje velikim, odnosno malim slovima
	4.5. "exit" - završava program s izvođenjem
5. Bonus points: Neka se svaki korisnikov zahtjev izvršava asinkrono, u nekoj od pomoćnih dretvi

Primjer korištenja programa:

#java Demo /tmp/files/students.csv

Dobrodosao dragi korisnice...
Studenti su uspjesno ucitani!

> create 0036577229 Marko Markić 4
Studen uspjesno kreiran!
> read 0036577229
{jmbag: 0036577229, ime: Marko, prezime: Markić, ocjena: 4}
> create 0036577230 Pero Perić 5
Studen uspjesno kreiran!
> filter-grade 3
Greska! Nedostaje relacija.
> filter-grade 3 g
{jmbag: 0036577229, ime: Marko, prezime: Markić, ocjena: 4}
{jmbag: 0036577230, ime: Pero, prezime: Perić, ocjena: 5}
> create 0036577231 Petar Horvat 2
Studen uspjesno kreiran!
> filter-name p
Pero Perić
Petar Horvat
> filter-name p -u
PERO PERIĆ
PETAR HORVAT
> exit
...

