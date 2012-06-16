Bozels
======

An Angry Birds clone made for the Programming II course at Ghent University. No
copyright infringement intended.

(No English in-depth guide available.)


Het spel
========

De basisstructuur van het spel zelf (zonder de configuratie) bestaat uit de
volgende klassen die met elkaar interageren:

Er zijn twee modellen, Simulation en LaunchModel, die de hoofdmodellen zijn en
doen wat hun naam doet vermoeden. Simulation zorgt voor alles wat met draden,
het pauzeren en herstarten te maken heeft. Ook de hoofdlus bevindt zich daar
dus. Daarin wordt om de <tijdstap> seconden een stap vooruit gezet in de wereld,
worden eventuele gebroken objecten verwijderd, wordt er eventueel een nieuwe
bozel op het lanceerplatform gezet en wordt er gekeken of het level nog niet ten
einde is.

LaunchModel houdt zich bezig met het lanceren en het vliegen van de bozels. Dit
object weet op ieder moment welke bozels er aan het vliegen zijn, of de laatste
bozel al zijn eventuele speciale actie heeft gedaan en welke bozel er al klaar
staat om afgeschoten te worden (als er tenminste één is).
  
Het constant bijhouden van de positie van de bozel in de katapult bevindt zich
echter in LaunchListener, een luisteraar naar muisgebeurtenissen van het spel-
paneel. Dit houdt zich enkel bezig met het juist verslepen van de bozel die moet
afgevuurd worden. Het lanceren zelf wordt doorgestuurd naar LaunchModel.
	
Het spelpaneel, GamePanel, luistert naar verandering van zowel Simulation als
LaunchModel. Bij een verandering hertekent het paneel alle elementen van het pa-
neel en eventuele bijkomende zaken naargelang de instellingen (e.g. explosie-
stralen, snelheidsvectoren, zwaartepunten).
    
UnitsHandler en BreakingUnitsHandler zijn de klassen die instaan voor het beheer
van de elementen in de wereld. Iedere klasse die de elementen van de wereld wilt
opvragen of iets wilt weten over de elementen (bv. is element x al vernietigd)
doet dit via UnitsHandler. BreakingUnitsHandler houdt op ieder moment bij hoe-
veel schade een element heeft opgelopen en berekend bij iedere nieuwe stoot of
het object al dan niet moet breken.
    
ClashListener ten slotte luistert naar botsingen in de wereld en geeft deze door
aan UnitsHandler/BreakingUnitsHandler.


Bozels, doelen en decor
=======================

Al deze elementen worden in de code 'units' genoemd. Alles hiervan bevindt zich
dan ook in de units-package. Hierin bevindt zich de volledige overervingsboom
van alle units en hun bijhorende factories. Aangezien bozels speciale acties
hebben, verdienen ze hun eigen subklassen en hun eigen package hierin. Ook de
factories (drie in totaal) met bijhorende exceptions hebben een aparte subpack-
age.

BozelFactory verdient hier misschien wat meer aandacht. Deze houdt een Map bij
die de types bozels afbeelden op de overeenkomstige klassen. Bij het aanmaken
van een nieuwe bozel wordt dan dynamisch de constructor van die klasse opgeroe-
pen.

In de package shapes zitten klassen die alle vormen moeten voorstellen die in de
opgave voorkomen. Elk van deze zijn subklassen van de klasse UnitShape die op
zijn beurt eist dat zijn subklassen twee methodes implementeren. Eén methode die
van de vorm een JBox2DShape teruggeeft en één die de vorm tekent op een meegege-
ven Graphics-object.


Load, load, load the xml
========================

De package bozels.initialization bevat twee klassen. Enerzijds een Initializer,
die een JFileChooser opent en het geselecteerde xml-bestand laat inlezen door
een XMLReader-object en aan Simulation doorgeeft dat UnitsHandler geüpdatet is.
Anderzijds hebben we een XMLReader die a.d.h.v. factories een xml-element omzet
in een unit van de juiste klasse.


Layout
======

Om u even wegwijs te maken in de interne layout van heel het venster, heb ik
hieronder een kort overzichtje gemaakt met de componentenhiërarchie en hun bij-
horende layoutmanager. Wat elk van hen doet, kan je gewoonweg zien bij het open-
en van het programma.

ContentPane(BorderLayout)
    -> CENTER: GamePanel
    -> SOUTH: ConfigurationPanel(BorderLayout)
        -> WEST: Pauze- en herstartenknop
        -> CENTER: JTabbedPane met twee soorten tabs
            -> WorldSettingsPanel(GroupLayout)
            -> UnitSettingsPanel(BorderLayout)
                -> WEST: Lijst met soorten
                -> CENTER: UnitSettingsFieldPanel(GroupLayout)
                    -> Breakable- of Unbreakable-


Configuratie en instellingen
============================

Iedere klasse die afhangt van instellingen die aan te passen zijn in het confi-
guratiepaneel is een luisteraar naar ofwel een WorldSettingsobject ofwel een
UnitSettingobject. Het WorldSettingsobject houdt de instellingen bij van het
tabblad "Algemeen"; de verschillende UnitSettingsobjecten houden de instellingen
bij van de verschillende soorten elementen in de wereld.

De klasse WorldSettings is opgebouwd volgens het singletonpatroon. De belang-
rijkste klassen van het spel (Simulation, LaunchModel, GamePanel en Clash-
Listener) luisteren naar dit object. Elk van hen wordt op de hoogte gebracht als
de gebruiker een wijziging doorvoert in het tabblad "Algemeen".

De klasse UnitSettings is opgebouwd volgens het multitonpatroon en houdt voor
ieder soort unit (e.g. hout, ijs, kleine doelen, rode bozels, etc.) zijn instel-
lingen bij. Iedere unit van die soort luistert dan ook naar het bijhorende Unit-
Settingsobject. Net zoals bij de algemene instellingen, wordt ook hier iedere
unit van die soort op de hoogte gesteld als er een aanpassing wordt gedaan door
de gebruiker.