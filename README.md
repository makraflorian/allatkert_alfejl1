#Alkalmazásfejlesztés I. - Állatkert - Makra Flórián Róbert - DRGIP2

##Leírás:
###A feladat egy állatkerti alkalmazás létrehozása, ahol lehetőség nyílik az örökbefogadásra, és a dolgozók is elláthatják feladataikat.

##Feature lista:
###Asztali felület:
```
Látogatók(örökbefogadók) listázása, szerkesztése, törlése
Állatok felvitele az adatbázisba
Állatok listázása, szűrése(örökbefogadottság alapján), szerkesztése, törlése
Örökbefogadások listázása, szűrése(örökbefogadó név, állat név, örökbefogadás éve, támogatás tipusa alapján)
```
###Webes felület
```
Lehet regisztráni(örökbefogadó felvitele az adatbázisba)
Állatok listázása, szűrése(van e neve az állatnak), itt megtekinthetnek minden fontosabb információt az állatról.
Örökbefogadás(a mezőket kitöltve örökbefogadhatják a választott állatot)
```

##Szükséges beállítások a futtatáshoz:
```
Az allatkert.db(core alatt resources/allatkert.db) abszolút útvonalát átállítani a saját abszolút útvonalunkra(core alatt resources/application.properties file-ban).<br>
```
```
Tomcat server beállítása, konfigurálása
```
a root a jobboldali maven-es fülön a core és a desktop alatt helyezkedik el(kicsit zavaró lehet elsőre)