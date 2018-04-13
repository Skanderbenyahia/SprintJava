-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 13 avr. 2018 à 00:27
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pidev`
--

-- --------------------------------------------------------

--
-- Structure de la table `animal`
--

DROP TABLE IF EXISTS `animal`;
CREATE TABLE IF NOT EXISTS `animal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_refuge` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `espece` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `race` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `age` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sexe` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `taille` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `region` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `demande` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_6AAB231FDBDE2A6A` (`id_refuge`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `animal`
--

INSERT INTO `animal` (`id`, `id_refuge`, `nom`, `espece`, `race`, `age`, `sexe`, `taille`, `region`, `description`, `etat`, `image`, `demande`) VALUES
(3, NULL, 'kiki', 'chat', 'chat', 'junior', 'male', 'petit', 'tunis', '\ndfsdfsd', 'nonadopte', 'dfdf', 1),
(4, NULL, 'ragnar', 'chien', 'berger', 'junior', 'male', 'grand', 'tunis', '\naaaaaaaaaaaa', 'nonadopte', 'dog1.jpeg', 1),
(5, NULL, 'floki', 'chien', 'caniche', 'junior', 'femelle', 'petit', 'la marsa ', 'Floki FLOKIFLOKIFLOKIFLOKIFLOKIFLOKIFLOKIFLOKIFLOKI', 'nonadopte', 'dog1.jpeg', 0);

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `libelle`) VALUES
(5, 'Aliments'),
(7, 'Accessoires'),
(8, 'Test'),
(9, 'aa');

-- --------------------------------------------------------

--
-- Structure de la table `centre_dressage`
--

DROP TABLE IF EXISTS `centre_dressage`;
CREATE TABLE IF NOT EXISTS `centre_dressage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` int(11) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ville` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gouvernerat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code_postal` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `centre_dressage`
--

INSERT INTO `centre_dressage` (`id`, `libelle`, `adresse`, `tel`, `lat`, `lng`, `description`, `image`, `ville`, `gouvernerat`, `code_postal`) VALUES
(1, 'ok', 'ok', 21456789, 36.800899505615234, 10.185356140136719, 'djqidjq', 'jsdfihfdsq', 'tunis', 'tunis', 2048),
(3, 'alo', 'alo', 21212121, 36.89911651611328, 10.273933410644531, 'alo', 'alo', 'aloo', 'aloo', 2554),
(4, 'aoaoaoao', 'aoaoaoao', 21212121, 36.82440185546875, 9.839973449707031, 'aiaoaiaiai', 'aoaiaiaoiao', 'oallao', 'aolalao', 2014);

-- --------------------------------------------------------

--
-- Structure de la table `centre_toilettage`
--

DROP TABLE IF EXISTS `centre_toilettage`;
CREATE TABLE IF NOT EXISTS `centre_toilettage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tel` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `centre_toilettage`
--

INSERT INTO `centre_toilettage` (`id`, `libelle`, `adresse`, `tel`, `description`, `image`) VALUES
(63, 'xxxooobbbbobobo', 'xxxooo', 78, 'kais', 'file:/C:/Users/Mimouna/Documents/vet.jpg'),
(70, 'ga', 'gg', 44, 'gf', 'file:/C:/Users/Mimouna/Desktop/3.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `commandes`
--

DROP TABLE IF EXISTS `commandes`;
CREATE TABLE IF NOT EXISTS `commandes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_instruction_id` int(11) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_commande` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_35D4282C8789B572` (`payment_instruction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `commandes`
--

INSERT INTO `commandes` (`id`, `payment_instruction_id`, `amount`, `description`, `date_commande`) VALUES
(2, NULL, 190083, '1*adaqd', '2018-04-09'),
(3, NULL, 63325, '1*test', '2018-04-12'),
(4, NULL, 126633, '2*test', '2018-04-12');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contenu` varchar(255) NOT NULL,
  `nbrSignal` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  `idCentre` int(11) DEFAULT NULL,
  `date_com` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idUser` (`idUser`),
  KEY `idCentre` (`idCentre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `concours`
--

DROP TABLE IF EXISTS `concours`;
CREATE TABLE IF NOT EXISTS `concours` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nbredeplaces` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `concours`
--

INSERT INTO `concours` (`id`, `description`, `nbredeplaces`, `date`) VALUES
(1, 'okii', 45, '2018-04-12');

-- --------------------------------------------------------

--
-- Structure de la table `conseils`
--

DROP TABLE IF EXISTS `conseils`;
CREATE TABLE IF NOT EXISTS `conseils` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `text` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `conseils`
--

INSERT INTO `conseils` (`id`, `titre`, `text`, `type`) VALUES
(1, 'aloo', 'nn', 'nn');

-- --------------------------------------------------------

--
-- Structure de la table `credits`
--

DROP TABLE IF EXISTS `credits`;
CREATE TABLE IF NOT EXISTS `credits` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_instruction_id` int(11) NOT NULL,
  `payment_id` int(11) DEFAULT NULL,
  `attention_required` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `credited_amount` decimal(10,5) NOT NULL,
  `crediting_amount` decimal(10,5) NOT NULL,
  `reversing_amount` decimal(10,5) NOT NULL,
  `state` smallint(6) NOT NULL,
  `target_amount` decimal(10,5) NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_4117D17E8789B572` (`payment_instruction_id`),
  KEY `IDX_4117D17E4C3A3BB` (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `financial_transactions`
--

DROP TABLE IF EXISTS `financial_transactions`;
CREATE TABLE IF NOT EXISTS `financial_transactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `credit_id` int(11) DEFAULT NULL,
  `payment_id` int(11) DEFAULT NULL,
  `extended_data` longtext COLLATE utf8_unicode_ci COMMENT '(DC2Type:extended_payment_data)',
  `processed_amount` decimal(10,5) NOT NULL,
  `reason_code` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reference_number` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `requested_amount` decimal(10,5) NOT NULL,
  `response_code` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state` smallint(6) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `tracking_id` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `transaction_type` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_1353F2D9CE062FF9` (`credit_id`),
  KEY `IDX_1353F2D94C3A3BB` (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `genj_faq_category`
--

DROP TABLE IF EXISTS `genj_faq_category`;
CREATE TABLE IF NOT EXISTS `genj_faq_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `headline` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `body` longtext COLLATE utf8_unicode_ci,
  `rank` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `slug` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `is_active_idx` (`is_active`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `genj_faq_question`
--

DROP TABLE IF EXISTS `genj_faq_question`;
CREATE TABLE IF NOT EXISTS `genj_faq_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `headline` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `body` longtext COLLATE utf8_unicode_ci,
  `rank` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `publish_at` datetime NOT NULL,
  `expires_at` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `slug` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_375D163F12469DE2` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `genj_faq_search`
--

DROP TABLE IF EXISTS `genj_faq_search`;
CREATE TABLE IF NOT EXISTS `genj_faq_search` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `headline` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `search_count` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `slug` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `search_count_idx` (`search_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `jaime`
--

DROP TABLE IF EXISTS `jaime`;
CREATE TABLE IF NOT EXISTS `jaime` (
  `id_jaime` int(10) NOT NULL AUTO_INCREMENT,
  `id_user` int(10) DEFAULT NULL,
  `id_centre` int(10) DEFAULT NULL,
  `etat` int(11) NOT NULL,
  `etat2` int(11) NOT NULL,
  PRIMARY KEY (`id_jaime`),
  KEY `id_user` (`id_user`),
  KEY `id_centre` (`id_centre`)
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `jaime`
--

INSERT INTO `jaime` (`id_jaime`, `id_user`, `id_centre`, `etat`, `etat2`) VALUES
(182, 20, 70, 0, 1),
(184, 20, 63, 1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `ligne`
--

DROP TABLE IF EXISTS `ligne`;
CREATE TABLE IF NOT EXISTS `ligne` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idClient` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `Image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `produit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_57F0DB8329A5EC27` (`produit`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_B6BD307FE2904019` (`thread_id`),
  KEY `IDX_B6BD307FF624B39D` (`sender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `message_metadata`
--

DROP TABLE IF EXISTS `message_metadata`;
CREATE TABLE IF NOT EXISTS `message_metadata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_read` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_4632F005537A1329` (`message_id`),
  KEY `IDX_4632F0059D1C3019` (`participant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

DROP TABLE IF EXISTS `participation`;
CREATE TABLE IF NOT EXISTS `participation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idc` int(11) DEFAULT NULL,
  `idp` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_AB55E24F6D6DB7FC` (`idc`),
  KEY `IDX_AB55E24FE9D3F622` (`idp`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `participation`
--

INSERT INTO `participation` (`id`, `idc`, `idp`) VALUES
(1, 1, 13),
(2, 1, 13),
(3, 1, 13),
(4, 1, 13),
(5, 1, 13),
(6, 1, 13),
(7, 1, 13);

-- --------------------------------------------------------

--
-- Structure de la table `payments`
--

DROP TABLE IF EXISTS `payments`;
CREATE TABLE IF NOT EXISTS `payments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_instruction_id` int(11) NOT NULL,
  `approved_amount` decimal(10,5) NOT NULL,
  `approving_amount` decimal(10,5) NOT NULL,
  `credited_amount` decimal(10,5) NOT NULL,
  `crediting_amount` decimal(10,5) NOT NULL,
  `deposited_amount` decimal(10,5) NOT NULL,
  `depositing_amount` decimal(10,5) NOT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `reversing_approved_amount` decimal(10,5) NOT NULL,
  `reversing_credited_amount` decimal(10,5) NOT NULL,
  `reversing_deposited_amount` decimal(10,5) NOT NULL,
  `state` smallint(6) NOT NULL,
  `target_amount` decimal(10,5) NOT NULL,
  `attention_required` tinyint(1) NOT NULL,
  `expired` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_65D29B328789B572` (`payment_instruction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `payment_instructions`
--

DROP TABLE IF EXISTS `payment_instructions`;
CREATE TABLE IF NOT EXISTS `payment_instructions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,5) NOT NULL,
  `approved_amount` decimal(10,5) NOT NULL,
  `approving_amount` decimal(10,5) NOT NULL,
  `created_at` datetime NOT NULL,
  `credited_amount` decimal(10,5) NOT NULL,
  `crediting_amount` decimal(10,5) NOT NULL,
  `currency` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `deposited_amount` decimal(10,5) NOT NULL,
  `depositing_amount` decimal(10,5) NOT NULL,
  `extended_data` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:extended_payment_data)',
  `payment_system_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `reversing_approved_amount` decimal(10,5) NOT NULL,
  `reversing_credited_amount` decimal(10,5) NOT NULL,
  `reversing_deposited_amount` decimal(10,5) NOT NULL,
  `state` smallint(6) NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categorie` int(11) DEFAULT NULL,
  `libelle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prix` int(11) NOT NULL,
  `animal` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `quantite` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_29A5EC27497DD634` (`categorie`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `categorie`, `libelle`, `description`, `prix`, `animal`, `Image`, `quantite`) VALUES
(11, 5, 'Croquette', 'Gout Poisson', 25, 'Chat', 'ultima.jpeg', 50),
(12, 5, 'test', 'test', 63254, 'chat', 'aliment.jpeg', 50),
(13, 7, 'emn', 'mimoun', 200, 'wael kleb', 'file:/C:/Users/bn-sk/Pictures/sousse/DSC_0004.JPG', 13);

-- --------------------------------------------------------

--
-- Structure de la table `rating`
--

DROP TABLE IF EXISTS `rating`;
CREATE TABLE IF NOT EXISTS `rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idc` int(11) NOT NULL,
  `note` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idc` (`idc`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `rating`
--

INSERT INTO `rating` (`id`, `idc`, `note`) VALUES
(1, 1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `refuge`
--

DROP TABLE IF EXISTS `refuge`;
CREATE TABLE IF NOT EXISTS `refuge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `num` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `region` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `refuge`
--

INSERT INTO `refuge` (`id`, `libelle`, `num`, `email`, `region`, `adresse`, `description`, `image`) VALUES
(2, 'libelle', 456, 'image', 'adresse', 'email', '\ndescription', 'ultima.jpeg'),
(3, 'libelle', 25674524, 'email', 'region', 'adresse', '\ndescription', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `reservation_petsitter`
--

DROP TABLE IF EXISTS `reservation_petsitter`;
CREATE TABLE IF NOT EXISTS `reservation_petsitter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateD` date NOT NULL,
  `dateF` date NOT NULL,
  `prix` double NOT NULL,
  `encaisser` double NOT NULL,
  `idPetsitter` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_8471FEE562DCB42C` (`idPetsitter`),
  KEY `IDX_8471FEE5FE6E88D7` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservation_veterinaire`
--

DROP TABLE IF EXISTS `reservation_veterinaire`;
CREATE TABLE IF NOT EXISTS `reservation_veterinaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user_id` int(11) DEFAULT NULL,
  `id_veterinaire_id` int(11) DEFAULT NULL,
  `date_debut` datetime NOT NULL,
  `date_fin` datetime NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_25B5BFE479F37AE5` (`id_user_id`),
  KEY `IDX_25B5BFE438AEB422` (`id_veterinaire_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reservation_veterinaire`
--

INSERT INTO `reservation_veterinaire` (`id`, `id_user_id`, `id_veterinaire_id`, `date_debut`, `date_fin`, `description`) VALUES
(42, 20, 10, '2018-04-27 12:00:00', '2018-04-27 13:30:00', 'operation'),
(43, 20, 10, '2018-04-27 13:31:00', '2018-04-27 15:01:00', 'operation'),
(44, 20, 10, '2018-07-11 18:20:00', '2018-07-11 19:50:00', 'operation'),
(45, 20, 24, '2018-09-13 12:05:00', '2018-09-13 12:35:00', 'consultation'),
(46, 17, 17, '2018-04-14 04:34:00', '2018-04-14 05:04:00', 'consultation'),
(47, 17, 17, '2018-04-14 06:01:00', '2018-04-14 06:31:00', 'consultation');

-- --------------------------------------------------------

--
-- Structure de la table `signaler`
--

DROP TABLE IF EXISTS `signaler`;
CREATE TABLE IF NOT EXISTS `signaler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cause` varchar(50) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idcom` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `iduser_fk` (`iduser`),
  KEY `idcom_fk` (`idcom`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `thread`
--

DROP TABLE IF EXISTS `thread`;
CREATE TABLE IF NOT EXISTS `thread` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by_id` int(11) DEFAULT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `is_spam` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_31204C83B03A8386` (`created_by_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `thread_metadata`
--

DROP TABLE IF EXISTS `thread_metadata`;
CREATE TABLE IF NOT EXISTS `thread_metadata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `last_participant_message_date` datetime DEFAULT NULL,
  `last_message_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_40A577C8E2904019` (`thread_id`),
  KEY `IDX_40A577C89D1C3019` (`participant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `telephone` int(11) NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `ville` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gouvernerat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code_postal` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `prenom`, `adresse`, `telephone`, `latitude`, `longitude`, `ville`, `gouvernerat`, `code_postal`) VALUES
(13, 'test', NULL, 'test@gmail.com', NULL, 0, NULL, 'test', NULL, NULL, NULL, 'ROLE_CLIENT', 'test', 'test', 'test', 54547545, NULL, NULL, NULL, NULL, NULL),
(15, 'tazou', NULL, 'moataz', NULL, 1, NULL, 'love', NULL, NULL, NULL, 'ROLE_CLIENT', 'moataz', 'moataz', 'moataz', 214254, NULL, NULL, NULL, NULL, NULL),
(16, 'saly', NULL, 'saly', NULL, 0, NULL, 'saly', NULL, NULL, NULL, 'ROLE_CLIENT', 'saly', 'saly', 'saly', 123456789, NULL, NULL, NULL, NULL, NULL),
(17, 'vet', NULL, 'bn.skander@gmail.com', NULL, 0, NULL, 'vet', NULL, NULL, NULL, 'ROLE_VETERINAIRE', 'vet', 'vet', 'vet', 21231545, NULL, NULL, NULL, NULL, NULL),
(18, 'a', NULL, 'bn.skander@gmail.com', NULL, 0, NULL, 'a', NULL, NULL, NULL, 'ROLE_CLIENT', 'aaa', 'aaa', 'aaa', 21458784, NULL, NULL, NULL, NULL, NULL),
(19, 'az', NULL, 'bn.skander@gmail.com', NULL, 0, NULL, '$2y$13$Zk5pGMv5yZiZB1xC.B7EW.kPqPSBsqReyBSyoHuRiepFNs9UDj5yi', NULL, NULL, NULL, 'ROLE_CLIENT', 'bb', 'bb', 'b', 21454544, NULL, NULL, NULL, NULL, NULL),
(20, 'admin', NULL, 'bn.skander@gmail.com', NULL, 0, NULL, '$2y$13$hxHt3IbHZ3jmaH8DYzJdv.V2O9gjXOVc3FdFrmLwEPqP7OAPRadlC', NULL, NULL, NULL, 'ROLE_ADMIN', 'Ben Yahia', 'Skander', 'tunis', 50419220, NULL, NULL, NULL, NULL, NULL),
(21, 'membre', NULL, 'bn.skander@gmail.com', NULL, 1, NULL, '$2y$13$MXwQsgc0BbygZ3IEE57ui.I808X9ABx2k4iqfP7SANcDlVm4bQJJS', NULL, NULL, NULL, 'ROLE_CLIENT', 'membre', 'membre', 'tunis', 21456789, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
CREATE TABLE IF NOT EXISTS `wishlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `id_animal` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `wishlist`
--

INSERT INTO `wishlist` (`id`, `id_client`, `id_animal`) VALUES
(8, 13, 3);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `FK_35D4282C8789B572` FOREIGN KEY (`payment_instruction_id`) REFERENCES `payment_instructions` (`id`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `commentaire_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `commentaire_ibfk_2` FOREIGN KEY (`idCentre`) REFERENCES `centre_toilettage` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `credits`
--
ALTER TABLE `credits`
  ADD CONSTRAINT `FK_4117D17E4C3A3BB` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_4117D17E8789B572` FOREIGN KEY (`payment_instruction_id`) REFERENCES `payment_instructions` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `financial_transactions`
--
ALTER TABLE `financial_transactions`
  ADD CONSTRAINT `FK_1353F2D94C3A3BB` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_1353F2D9CE062FF9` FOREIGN KEY (`credit_id`) REFERENCES `credits` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `genj_faq_question`
--
ALTER TABLE `genj_faq_question`
  ADD CONSTRAINT `FK_375D163F12469DE2` FOREIGN KEY (`category_id`) REFERENCES `genj_faq_category` (`id`);

--
-- Contraintes pour la table `ligne`
--
ALTER TABLE `ligne`
  ADD CONSTRAINT `FK_57F0DB8329A5EC27` FOREIGN KEY (`produit`) REFERENCES `produit` (`id`);

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_B6BD307FE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  ADD CONSTRAINT `FK_B6BD307FF624B39D` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `message_metadata`
--
ALTER TABLE `message_metadata`
  ADD CONSTRAINT `FK_4632F005537A1329` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`),
  ADD CONSTRAINT `FK_4632F0059D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `FK_AB55E24F6D6DB7FC` FOREIGN KEY (`idc`) REFERENCES `concours` (`id`),
  ADD CONSTRAINT `FK_AB55E24FE9D3F622` FOREIGN KEY (`idp`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `FK_65D29B328789B572` FOREIGN KEY (`payment_instruction_id`) REFERENCES `payment_instructions` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_29A5EC27497DD634` FOREIGN KEY (`categorie`) REFERENCES `categorie` (`id`);

--
-- Contraintes pour la table `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `rating_ibfk_1` FOREIGN KEY (`idc`) REFERENCES `concours` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reservation_petsitter`
--
ALTER TABLE `reservation_petsitter`
  ADD CONSTRAINT `FK_8471FEE562DCB42C` FOREIGN KEY (`idPetsitter`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_8471FEE5FE6E88D7` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `signaler`
--
ALTER TABLE `signaler`
  ADD CONSTRAINT `idcom_fk` FOREIGN KEY (`idcom`) REFERENCES `commentaire` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `iduser_fk` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `thread`
--
ALTER TABLE `thread`
  ADD CONSTRAINT `FK_31204C83B03A8386` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  ADD CONSTRAINT `FK_40A577C89D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_40A577C8E2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
