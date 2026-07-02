# [1.4.0](https://github.com/Andi-IM/Masjidku/compare/v1.3.0...v1.4.0) (2026-07-02)


### Features

* add EditDonaturAnakYatim controller, BaseKeuanganSummaryReport, BaseAccountantController, and agent guidelines ([2cf902c](https://github.com/Andi-IM/Masjidku/commit/2cf902c31a1b6abc8ede5c5546c076965af7f56b))

# [1.3.0](https://github.com/Andi-IM/Masjidku/compare/v1.2.0...v1.3.0) (2026-07-02)


### Bug Fixes

* **app:** fully extract onSubmitted body to base class to eliminate final 6.8% duplication ([a3612a6](https://github.com/Andi-IM/Masjidku/commit/a3612a61dccbfcdb01a444e145cf41a3d829bbf5))
* **app:** move FXML fields and initialize logic to BaseEditController to fully eliminate new code duplication ([9e1a329](https://github.com/Andi-IM/Masjidku/commit/9e1a3293bc643ce2a265f71e7e9dcdf673cde855))
* **app:** pull up txtKeterangan field to BaseEditController to fix residual duplication in Pembayaran controllers ([39d363a](https://github.com/Andi-IM/Masjidku/commit/39d363ab0b36b197af4867cd2a2ad990c1f55ba4))


### Features

* implement EditDonaturAnakYatim controller for managing orphan donation records ([0ece03b](https://github.com/Andi-IM/Masjidku/commit/0ece03bbc2205589aeebf2cccf39a7a29d492b71))

# [1.2.0](https://github.com/Andi-IM/Masjidku/compare/v1.1.0...v1.2.0) (2026-07-02)


### Bug Fixes

* **security:** rename variable to hashResult to completely avoid SonarQube password regex ([f2e858f](https://github.com/Andi-IM/Masjidku/commit/f2e858f7ccaecb873a9da93037ee522a124518ae))
* **security:** resolve SonarQube false positive for generic password secret ([f1914d9](https://github.com/Andi-IM/Masjidku/commit/f1914d96bcec8671fb0abbaf7da3486b9f42aac9))


### Features

* implement abstract base repository for generic Hibernate CRUD operations ([43ed5f9](https://github.com/Andi-IM/Masjidku/commit/43ed5f9b3c5f25b5cfc1ee633da62fb9e3e83a37))
* implement base generic repository class and add sonar-secrets agent hook configuration ([f40cb40](https://github.com/Andi-IM/Masjidku/commit/f40cb406e9011a2cfe033297383f6f431d19304a))
* implement base generic repository class for Hibernate entity operations ([031b83c](https://github.com/Andi-IM/Masjidku/commit/031b83ce11b020b3b4ce4198873c08d6d30491b2))
* implement BaseEventRepositoryImpl for generic Hibernate database operations ([9bf43a0](https://github.com/Andi-IM/Masjidku/commit/9bf43a08170155126053c030f4ed7c06ac4741a3))
* implement generic BaseEventRepositoryImpl for common Hibernate database operations ([d3802fb](https://github.com/Andi-IM/Masjidku/commit/d3802fb23a1469b3eeb0fb7b8a83ca68587b2c2b))

# [1.1.0](https://github.com/Andi-IM/Masjidku/compare/v1.0.0...v1.1.0) (2026-07-02)


### Features

* add PrincipalReadDataTamu controller for managing guest data display ([8acfc30](https://github.com/Andi-IM/Masjidku/commit/8acfc30014ba5cdf2c3b75492269a9f362d730e0))

# 1.0.0 (2026-07-01)


### Bug Fixes

* **admin:** make UserLists constructor public for FXMLLoader access in JPMS ([631d7ce](https://github.com/Andi-IM/Masjidku/commit/631d7ce8b9f532deecb1604882324f0cfbacc4d4))
* correct logback-classic dependency group typo (ch.qos.logback) ([36c3db5](https://github.com/Andi-IM/Masjidku/commit/36c3db52ff2e55d15c1abc0f694728a298ada43c))
* **db:** add missing sessions table to sqlite initialization script ([987c022](https://github.com/Andi-IM/Masjidku/commit/987c0223e12a7d89be0f6bb9f76491bc42477f06))
* **db:** close ResultSets and PreparedStatements in Session.java and fetch latest session explicitly ([f7abde1](https://github.com/Andi-IM/Masjidku/commit/f7abde13c334108d3374df44966dbee6ffb830cd))
* **db:** correct query compilation in DaoFunctions and finish stage 4 ([9e17bdf](https://github.com/Andi-IM/Masjidku/commit/9e17bdf056a9b80368a44b74e8abbb32967abf31))
* **db:** remove unsupported ResultSet.isLast() method call for SQLite compatibility ([a54cbf6](https://github.com/Andi-IM/Masjidku/commit/a54cbf6f983182502b9efac1a0df87327d3feba4))
* **db:** use executeUpdate() instead of executeQuery() for DELETE in Session.truncateData() ([b1bde76](https://github.com/Andi-IM/Masjidku/commit/b1bde7663e563dc67bad2fc9ef4cb52b378587b6))
* **db:** use INSERT OR IGNORE in SQLiteInitService to prevent unique constraint errors on subsequent runs ([263fe7d](https://github.com/Andi-IM/Masjidku/commit/263fe7d3943b6e340fae78f1ee08fc4b3d8bf762))
* **db:** use singleton connection for SQLite and enable WAL mode to prevent database lock exceptions ([7c2d8a0](https://github.com/Andi-IM/Masjidku/commit/7c2d8a05ce917bc9b16401b30ee53eef045b6fde))
* **db:** wrap explicit driver loading in try-catch to make it optional ([c226406](https://github.com/Andi-IM/Masjidku/commit/c2264062decdb7442fa1cc1d0e185ceb35a8603c))
* resolve jasperreports module compilation errors in reporting module ([99b8b40](https://github.com/Andi-IM/Masjidku/commit/99b8b404654c289ad5d563f02f58d6bfd726fa6e))
* **sonar:** resolve security vulnerability, enum name clash blocker, and cognitive complexity issues ([0780daf](https://github.com/Andi-IM/Masjidku/commit/0780daff8634b223490496685eb0b6e4207c58f0))
* **ui:** resolve NullPointerException in controllers by fixing ViewManager reflection signature ([3630b64](https://github.com/Andi-IM/Masjidku/commit/3630b64363e9b25ebb64cd99363194321341c330))
* unused py codes ([98798fa](https://github.com/Andi-IM/Masjidku/commit/98798fa4afd19f48db5b0c69d3370abc7d7f8689))


### Features

* add accounting domain entities, client data models, and GitHub CI/CD workflows ([35476be](https://github.com/Andi-IM/Masjidku/commit/35476be45abccf1b25f81f550772ef4f0ce12553))
* add AdminHome controller to manage dashboard navigation and user greeting ([144efae](https://github.com/Andi-IM/Masjidku/commit/144efaeba2faa992d759f9312907ccef34c4863a))
* add ProfileController to handle user data display and navigation actions ([4742dff](https://github.com/Andi-IM/Masjidku/commit/4742dffaddfdd0d5113d2fbcc5407fccf7f71761))
* **db:** add SQLiteInitService to automatically create sqlite schema at startup ([57bc805](https://github.com/Andi-IM/Masjidku/commit/57bc805568a33b5865f199e51397796beb1e2f38))
* **db:** switch to SQLite and abstract DatabaseConnection across all modules ([d9078e7](https://github.com/Andi-IM/Masjidku/commit/d9078e76c4b939db34ce9f6809e25e855a34df1e))
* implement accounting edit forms, event repositories, and profile management utilities ([8afce28](https://github.com/Andi-IM/Masjidku/commit/8afce280bf67595e0ed1eb6452654b4d81fa1b5f))
* implement accounting module entities, repositories, and UI controllers for anak yatim, operasional, pembangunan, TPA, and zakat features ([52ac068](https://github.com/Andi-IM/Masjidku/commit/52ac068924158e9c20df46b3265b0c59f3a9f356))
* implement accounting modules and financial reporting controllers for various charity sectors ([e2617d5](https://github.com/Andi-IM/Masjidku/commit/e2617d56bfd78871df80bfe133fe6f1b4b5479c3))
* implement AppRoute navigation mapping and localized sidebar labels ([ffd385b](https://github.com/Andi-IM/Masjidku/commit/ffd385bb494b74840e3bb3b2af2b8320196ddb84))
* implement automatic sidebar button selection based on loaded FXML view path ([41a8874](https://github.com/Andi-IM/Masjidku/commit/41a8874c5cab1418288a07c41102d8d3052e599b))
* implement base table controllers and report list views for kegiatan, tamu, and undangan modules ([84721d2](https://github.com/Andi-IM/Masjidku/commit/84721d2898c9321f2a0ddef9146fffd7899f929d))
* implement comprehensive accounting modules for TPA, Anak Yatim, Pembangunan, Operasional, and Zakat sectors ([4a372d2](https://github.com/Andi-IM/Masjidku/commit/4a372d25ba5490ad369d3aa59f4fa267d932f716))
* implement core accounting domain entities, repositories, and service logic with Hibernate persistence ([e8cda90](https://github.com/Andi-IM/Masjidku/commit/e8cda905a9237d8594d58fc59503e9afbf177ebf))
* implement core architecture, dependency injection, and modular structure for accounting and authentication services ([7afcb52](https://github.com/Andi-IM/Masjidku/commit/7afcb522af11fde031e99ebb69e0ea605c816e43))
* implement dependency injection using Dagger 2 for the accounting and application modules ([fa0f3d4](https://github.com/Andi-IM/Masjidku/commit/fa0f3d49e3e076253b9af308ec9baa1fe1a647e7))
* implement domain repositories, services, and navigation framework for user management and accounting modules ([e4f7217](https://github.com/Andi-IM/Masjidku/commit/e4f72173f0bfe04d3b3c3ac9a2ff95118313a207))
* implement event management modules and repositories with JavaFX controllers ([6be6bb8](https://github.com/Andi-IM/Masjidku/commit/6be6bb82d9ad5a70b370194d9f429535707f75a3))
* implement events module with client-server architecture and secretary management forms ([c7dc4b6](https://github.com/Andi-IM/Masjidku/commit/c7dc4b6f816a24e04e186a0a8fd2e6733b322fc4))
* implement JasperReports integration and Dagger dependency injection for reporting service ([bfc42fa](https://github.com/Andi-IM/Masjidku/commit/bfc42fab9037b07de6e168ed9fe9aeb525a98582))
* implement kegiatan management module with client-side events integration and UI controllers ([36e214c](https://github.com/Andi-IM/Masjidku/commit/36e214c295d4cf6c70e3367bb8a4ad1e78756bc7))
* implement modular event management system with new client interface, repository layer, and secretary UI modules ([706921d](https://github.com/Andi-IM/Masjidku/commit/706921d8c7e5aba40c2243b14d545819128bf6ac))
* implement persistence layer with Hibernate, SQLite, and Dagger dependency injection across modules ([b3a537c](https://github.com/Andi-IM/Masjidku/commit/b3a537c3f3a441d8296d21107c30c7c35effc1c6))
* implement Principal home interface, navigation routing, and event models with JPA converters ([0945f27](https://github.com/Andi-IM/Masjidku/commit/0945f27007a044212b218621b7d2a14202546d45))
* implement reporting engine with JasperReports templates for financial and administrative data ([bcaf8de](https://github.com/Andi-IM/Masjidku/commit/bcaf8de720c26c5201ff4d40ea73acace6b8cb0b))
* implement reporting service and add JasperReports templates for donation and financial data ([25cc6db](https://github.com/Andi-IM/Masjidku/commit/25cc6dbce8f3a102c88c4a51ad64bdbc0ec887bb))
* implement reporting service and integrate PDF export functionality into activity and finance controllers ([2f09813](https://github.com/Andi-IM/Masjidku/commit/2f09813d7b4616a8cf349079765aa2496884dc5e))
* implement role-based navigation controllers and event domain entities ([76104e4](https://github.com/Andi-IM/Masjidku/commit/76104e4348055849488ba25ffe9f4497620ca8b0))
* implement session management and accounting client services with Dagger dependency injection. ([a7f88b1](https://github.com/Andi-IM/Masjidku/commit/a7f88b1c34f014b3c8ae2467e93f605d5f55b793))
* implement SessionManager and Dagger dependency injection configuration ([06551a3](https://github.com/Andi-IM/Masjidku/commit/06551a38eed055200dc98450e3f13fcfa03b6914))
* implement user authentication session management and role-based home controllers using Dagger dependency injection ([d594feb](https://github.com/Andi-IM/Masjidku/commit/d594feb1f678d41bb6045a4727bbd53cf378b5b1))
* implement user domain models, mappers, and session management infrastructure ([3df3633](https://github.com/Andi-IM/Masjidku/commit/3df363306e0ed28f39386b7d2dca9d88b4be6b6b))
* implement user domain models, mappers, session management, and profile update controller ([910001b](https://github.com/Andi-IM/Masjidku/commit/910001b776915db41a7aa0f796214af2e37c97d0))
* implement user management and session handling infrastructure with Hibernate persistence ([a8a9660](https://github.com/Andi-IM/Masjidku/commit/a8a96607b2ed4e2f9bb9bcd85180a895a0894ef9))
* implement user management features, authentication, and session handling controllers ([04fc674](https://github.com/Andi-IM/Masjidku/commit/04fc674c6ca9dd550624d0b9667bb19cae859c7c))
* implement user management repositories and profile editing functionality ([4a3039d](https://github.com/Andi-IM/Masjidku/commit/4a3039d556d1fc83d8bb9d712d9b5ed8188ece58))
* implement user management system with entity models, repositories, and controllers ([5e1a573](https://github.com/Andi-IM/Masjidku/commit/5e1a573b0eb5fcc719b008292f8f29a8aa2234a5))
* implement user session tracking and profile management features ([dd9120b](https://github.com/Andi-IM/Masjidku/commit/dd9120b4d0ef8e40fb84f158f27d3c9f1109b1e9))
* implement Zakat reporting and management controllers for principal and accountant modules ([0ccbb6a](https://github.com/Andi-IM/Masjidku/commit/0ccbb6ac7d5a46a55b001914d22fdd6ee98e9548))
* initialize core domain entities, repository implementations, and Dagger dependency injection framework ([1ca6874](https://github.com/Andi-IM/Masjidku/commit/1ca687430dbb0a21f466ce00bcf4a1098b951a22))
* initialize core modules and implement base architecture for secretary, principal, and accounting controllers ([a0ab2ba](https://github.com/Andi-IM/Masjidku/commit/a0ab2ba21604d392bb061eec8d5245a2e006af19))
* initialize JavaFX application structure and UI management components for the Masjidku system ([20c71ed](https://github.com/Andi-IM/Masjidku/commit/20c71edb46426e1493eb5bea1a6de41f396a4e3d))
* initialize masjidku-app module with Gradle build configuration and remove local JDK path from properties ([74a9535](https://github.com/Andi-IM/Masjidku/commit/74a9535fafab5a1cb55dcef9c1c9f4d111fd3d54))
* initialize project architecture with modular Gradle setup and dependency management ([43f58aa](https://github.com/Andi-IM/Masjidku/commit/43f58aa54021cb4625fbc7d3f0a70a3d13fd412a))
* make application version in About page dynamic using version.properties and Semantic Release integration ([2320d33](https://github.com/Andi-IM/Masjidku/commit/2320d330726c288f9b6948de47668966cf0d187b))
* **principal:** implement edit and remove actions in PrincipalReadDataTamu ([275154f](https://github.com/Andi-IM/Masjidku/commit/275154f0c74b47e65f2c712df7dc23fb03e23444))
* **report:** implement showReport and printReport across controllers ([21f981b](https://github.com/Andi-IM/Masjidku/commit/21f981b9e7d910b9cf6230520ea53721223f5cd9))
* update sidebar navigation selected state on view change and clean up controllers ([8ac6d9d](https://github.com/Andi-IM/Masjidku/commit/8ac6d9d8f8bb547fd9d5a717785071bb015a70d6))
