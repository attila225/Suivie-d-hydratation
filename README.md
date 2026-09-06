Hydratation
Une	application	Android	simple	de	suivi	d’hydratation	quotidienne,
écrite	en	Kotlin	avec	Jetpack	Compose.	Générée	initialement	avec
Google	AI	Studio,	puis	adaptée	pour	être	compilée	et	lancée	avec
Android	Studio	Meerkat	Feature	Drop	(2024.3.2).
Fonctionnalités
Enregistrement	rapide	de	consommations	d’eau	(boutons	de
quantités	prédéfinies,	ex.	+250	ml).
Jauge	circulaire	animée	montrant	la	progression	vers	l’objectif	du
jour	(2000	ml	par	défaut).
Historique	des	verres	bus	dans	la	journée,	avec	l’heure	de	chaque
entrée.
Possibilité	de	supprimer	une	entrée	(removeEntry)	ou	de
réinitialiser	la	journée	(reset).
Petite	animation	de	célébration	quand	l’objectif	est	atteint.
Thème	sombre	turquoise	personnalisé	(MyApplicationTheme).
Toute	la	logique	et	l’état	de	l’écran	vivent	dans	HydrationViewModel
(StateFlow<HydrationUiState>)	;	l’UI	(HydrationScreen.kt)	ne	fait
qu’observer	cet	état	—	aucune	base	de	données	ni	appel	réseau	n’est
utilisé,	tout	est	en	mémoire	(les	données	sont	perdues	si	l’app	est
fermée).
Structure	du	projet
app/src/main/java/com/example/
├──	MainActivity.kt														
HydrationScreen
├──	HydrationViewModel.kt								
└──	ui/
├──	HydrationScreen.kt							
@Preview
└──	theme/																			
Compose
Prérequis
#	Point	d'entrée,	héberge	
#	État	+	logique	métier	(StateFlow)
#	Écran	principal	+	fonctions	
#	Couleurs,	typographie,	thème	
Android	Studio	Meerkat	Feature	Drop	(2024.3.2)	ou	une
version	plus	récente	compatible.
JDK	17+	configuré	comme	“Gradle	JDK”	(Settings	>	Build	Tools	>
Gradle	>	Gradle	JDK).
Android	SDK	Platform	34	installé	(normalement	déjà	présent	par
défaut).
Un	appareil	Android	avec	minSdk	24	(Android	7.0)	ou	supérieur,
ou	un	émulateur.
Lancer	le	projet
1.	Décompresse	le	zip	et	ouvre	le	dossier	Hydratation	(celui	qui
contient	settings.gradle.kts)	dans	Android	Studio	—	pas	le	zip	lui
même.
2.	Laisse	le	sync	Gradle	se	terminer	(il	télécharge	Gradle	8.9	tout
seul	la	première	fois).
3.	Sélectionne	ton	appareil/émulateur	dans	la	barre	d’outils,	puis
clique	sur	le	bouton	▶	Run	vert	(celui	à	côté	de	la	config	“app”).
4.	Pour	voir	le	design	sans	lancer	d’émulateur	:	ouvre	
HydrationScreen.kt,	clique	sur	l’onglet	Split	ou	Design	en	haut	à
droite	de	l’éditeur.	N’utilise	pas	la	petite	icône	“Deploy	to	Device”
qui	apparaît	à	côté	d’un	@Preview	—	c’est	une	fonctionnalité
séparée	qui	installe	l’app	sur	un	appareil	et	qui	n’est	pas
nécessaire	pour	juste	visualiser	le	design.
Configuration	technique	(versions	figées
volontairement)
Ces	versions	ont	été	choisies	pour	être	compatibles	avec	Android
Studio	Meerkat	FD	2024.3.2	(le	projet	original	généré	par	AI	Studio
utilisait	des	versions	bien	trop	récentes	pour	cette	édition	d’Android
Studio)	:
Outil
Version
Android	Gradle	Plugin 8.7.3
Gradle
8.9
Kotlin
2.0.21
compileSdk	/	targetSdk 34
minSdk
24
Le	fichier	debug.keystore	référencé	par	le	projet	AI	Studio	d’origine
n’existait	pas	dans	l’export	—	la	signature	en	debug	a	été	simplifiée
pour	utiliser	la	signature	automatique	par	défaut	d’Android	Studio
(~/.android/debug.keystore).	Les	dépendances	inutilisées	dans	le	code
(Firebase,	Room,	Retrofit,	Moshi,	OkHttp,	Roborazzi,	plugin	Secrets)
ont	été	retirées	pour	simplifier	et	fiabiliser	le	build.
Dépannage
Le	sync	Gradle	échoue	avec	un	message	sur	android.useAndroidX
→	Vérifie	que	gradle.properties	contient	bien	la	ligne	
android.useAndroidX=true.
ClassNotFoundException	au	lancement	(l’app	s’ouvre	puis	se	ferme
aussitôt)	→	C’est	généralement	le	signe	qu’une	ancienne	installation
incomplète	traîne	sur	l’appareil.	Désinstalle	complètement	l’app
depuis	Paramètres	>	Applications	sur	le	téléphone,	fais	Build	>	Clean
Project	puis	Build	>	Rebuild	Project	dans	Android	Studio,	et	relance.
Sur	les	téléphones	Tecno/Infinix/itel	(ROM	HiOS/XOS),	pense	aussi	à
désactiver	temporairement	l’optimiseur/antivirus	intégré,	qui	peut
altérer	les	APK	de	debug	fraîchement	installées.
Le	panneau	Preview	(Split/Design)	reste	vide	→	Clique	sur	l’icône
“Build	&	Refresh”	(flèches	circulaires)	en	haut	du	panneau,	ou	fais
File	>	Invalidate	Caches	/	Restart	si	ça	persiste.
Licence
Projet	personnel	—	aucune	licence	spécifique	définie
NB : dans build.gradle.kts(app) on ajoute


android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_21
        targetCompatibility JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = '21'
    }
}
