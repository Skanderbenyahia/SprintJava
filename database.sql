-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 03, 2018 at 12:06 AM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pidev`
--

-- --------------------------------------------------------

--
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
CREATE TABLE IF NOT EXISTS `animal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_refuge` int(11) DEFAULT NULL,
  `taille` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `race` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `espece` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sexe` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `demande` int(11) NOT NULL,
  `region` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `age` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_6AAB231FDBDE2A6A` (`id_refuge`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`id`, `libelle`) VALUES
(5, 'Aliments'),
(7, 'Accessoires');

-- --------------------------------------------------------

--
-- Table structure for table `centre_dressage`
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `centre_toilettage`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `commandes`
--

DROP TABLE IF EXISTS `commandes`;
CREATE TABLE IF NOT EXISTS `commandes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_instruction_id` int(11) DEFAULT NULL,
  `amount` decimal(10,5) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_commande` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_35D4282C8789B572` (`payment_instruction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `commandes`
--

INSERT INTO `commandes` (`id`, `payment_instruction_id`, `amount`, `description`, `date_commande`) VALUES
(16, 12, '25.00000', '1*Croquette', '2018-03-02');

-- --------------------------------------------------------

--
-- Table structure for table `concours`
--

DROP TABLE IF EXISTS `concours`;
CREATE TABLE IF NOT EXISTS `concours` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nbredeplaces` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `concours`
--

INSERT INTO `concours` (`id`, `description`, `nbredeplaces`, `date`) VALUES
(2, 'azeasqdsd', 4874, '2018-04-11'),
(3, 'kdbvkdb', 45, '2018-04-19'),
(11, 'l', 4, '2018-04-12'),
(12, 'jbjvjv', 4, '2018-04-12');

-- --------------------------------------------------------

--
-- Table structure for table `conseils`
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
-- Dumping data for table `conseils`
--

INSERT INTO `conseils` (`id`, `titre`, `text`, `type`) VALUES
(1, 'bhhh', 'hh', 'Dogs');

-- --------------------------------------------------------

--
-- Table structure for table `credits`
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
-- Table structure for table `financial_transactions`
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `financial_transactions`
--

INSERT INTO `financial_transactions` (`id`, `credit_id`, `payment_id`, `extended_data`, `processed_amount`, `reason_code`, `reference_number`, `requested_amount`, `response_code`, `state`, `created_at`, `updated_at`, `tracking_id`, `transaction_type`) VALUES
(12, NULL, 13, NULL, '25.00000', 'none', '6LA37385752920931', '25.00000', 'success', 5, '2018-03-02 08:47:36', '2018-03-02 08:48:18', NULL, 2);

-- --------------------------------------------------------

--
-- Table structure for table `genj_faq_category`
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
-- Table structure for table `genj_faq_question`
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
-- Table structure for table `genj_faq_search`
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
-- Table structure for table `ligne`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `message`
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
-- Table structure for table `message_metadata`
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
-- Table structure for table `participation`
--

DROP TABLE IF EXISTS `participation`;
CREATE TABLE IF NOT EXISTS `participation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idc` int(11) DEFAULT NULL,
  `idp` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_AB55E24F6D6DB7FC` (`idc`),
  KEY `IDX_AB55E24FE9D3F622` (`idp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `payments`
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`id`, `payment_instruction_id`, `approved_amount`, `approving_amount`, `credited_amount`, `crediting_amount`, `deposited_amount`, `depositing_amount`, `expiration_date`, `reversing_approved_amount`, `reversing_credited_amount`, `reversing_deposited_amount`, `state`, `target_amount`, `attention_required`, `expired`, `created_at`, `updated_at`) VALUES
(13, 12, '25.00000', '0.00000', '0.00000', '0.00000', '25.00000', '0.00000', NULL, '0.00000', '0.00000', '0.00000', 8, '25.00000', 0, 0, '2018-03-02 08:47:36', '2018-03-02 08:48:18');

-- --------------------------------------------------------

--
-- Table structure for table `payment_instructions`
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `payment_instructions`
--

INSERT INTO `payment_instructions` (`id`, `amount`, `approved_amount`, `approving_amount`, `created_at`, `credited_amount`, `crediting_amount`, `currency`, `deposited_amount`, `depositing_amount`, `extended_data`, `payment_system_name`, `reversing_approved_amount`, `reversing_credited_amount`, `reversing_deposited_amount`, `state`, `updated_at`) VALUES
(12, '25.00000', '25.00000', '0.00000', '2018-03-02 08:47:35', '0.00000', '0.00000', 'EUR', '25.00000', '0.00000', 'a:5:{s:10:\"return_url\";a:3:{i:0;s:74:\"http://localhost/PIDEV/PETMYPET/web/app_dev.php/commande/16/payment/create\";i:1;b:1;i:2;b:1;}s:10:\"cancel_url\";a:3:{i:0;s:74:\"http://localhost/PIDEV/PETMYPET/web/app_dev.php/commande/16/payment/cancel\";i:1;b:1;i:2;b:1;}s:10:\"useraction\";a:3:{i:0;s:6:\"commit\";i:1;b:1;i:2;b:1;}s:22:\"express_checkout_token\";a:3:{i:0;s:20:\"EC-86603357HX559842B\";i:1;b:1;i:2;b:1;}s:15:\"paypal_payer_id\";a:3:{i:0;s:13:\"GHV7STW5GXGBJ\";i:1;b:1;i:2;b:1;}}', 'paypal_express_checkout', '0.00000', '0.00000', '0.00000', 4, '2018-03-02 08:48:18');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id`, `categorie`, `libelle`, `description`, `prix`, `animal`, `Image`, `quantite`) VALUES
(11, 5, 'Croquette', 'Gout Poisson', 25, 'Chat', '593d1cc7f109c9bc0890149c99c9ec12.jpeg', 50);

-- --------------------------------------------------------

--
-- Table structure for table `refuge`
--

DROP TABLE IF EXISTS `refuge`;
CREATE TABLE IF NOT EXISTS `refuge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `libelle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `num` int(11) NOT NULL,
  `region` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reservation_petsitter`
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
-- Table structure for table `reservation_veterinaire`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `thread`
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
-- Table structure for table `thread_metadata`
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
-- Table structure for table `user`
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_8D93D64992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_8D93D649A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_8D93D649C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `prenom`, `adresse`, `telephone`, `latitude`, `longitude`, `ville`, `gouvernerat`, `code_postal`) VALUES
(11, 'skan', 'skan', 'skander.benyahia@esprit.tn', 'skander.benyahia@esprit.tn', 1, NULL, '$2y$13$eHKg4I3MqlbLACZlbyS7SueqsXgg2/raONaECENBfi74sDywB67lG', '2018-03-02 08:45:52', NULL, NULL, 'a:2:{i:0;s:11:\"ROLE_CLIENT\";i:1;s:10:\"ROLE_ADMIN\";}', 'BEN YAHIA', 'Skander', 'Tunis, Tunisie', 50419220, NULL, NULL, NULL, NULL, NULL),
(12, 'membre', 'membre', 'bn.skander@gmail.com', 'bn.skander@gmail.com', 1, NULL, '$2y$13$.oCM0WGAiQZS4mOD9QVnye3ud3Fhd9G.JdOYd6.VpUiKu7SeE64pi', '2018-03-02 08:47:29', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'membre', 'membre', 'Tunis, Tunisie', 45455454, NULL, NULL, NULL, NULL, NULL),
(13, 'test', NULL, 'test@gmail.com', NULL, 0, NULL, 'test', NULL, NULL, NULL, 'ROLE_CLIENT', 'test', 'test', 'test', 54547545, NULL, NULL, NULL, NULL, NULL),
(14, 'admin', NULL, 'admin', NULL, 1, NULL, 'admin', NULL, NULL, NULL, 'ROLE_ADMIN', 'admin', 'admin', 'admin', 0, NULL, NULL, NULL, NULL, NULL);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `animal`
--
ALTER TABLE `animal`
  ADD CONSTRAINT `FK_6AAB231FDBDE2A6A` FOREIGN KEY (`id_refuge`) REFERENCES `refuge` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `FK_35D4282C8789B572` FOREIGN KEY (`payment_instruction_id`) REFERENCES `payment_instructions` (`id`);

--
-- Constraints for table `credits`
--
ALTER TABLE `credits`
  ADD CONSTRAINT `FK_4117D17E4C3A3BB` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_4117D17E8789B572` FOREIGN KEY (`payment_instruction_id`) REFERENCES `payment_instructions` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `financial_transactions`
--
ALTER TABLE `financial_transactions`
  ADD CONSTRAINT `FK_1353F2D94C3A3BB` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_1353F2D9CE062FF9` FOREIGN KEY (`credit_id`) REFERENCES `credits` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `genj_faq_question`
--
ALTER TABLE `genj_faq_question`
  ADD CONSTRAINT `FK_375D163F12469DE2` FOREIGN KEY (`category_id`) REFERENCES `genj_faq_category` (`id`);

--
-- Constraints for table `ligne`
--
ALTER TABLE `ligne`
  ADD CONSTRAINT `FK_57F0DB8329A5EC27` FOREIGN KEY (`produit`) REFERENCES `produit` (`id`);

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_B6BD307FE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  ADD CONSTRAINT `FK_B6BD307FF624B39D` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `message_metadata`
--
ALTER TABLE `message_metadata`
  ADD CONSTRAINT `FK_4632F005537A1329` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`),
  ADD CONSTRAINT `FK_4632F0059D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `FK_AB55E24F6D6DB7FC` FOREIGN KEY (`idc`) REFERENCES `concours` (`id`),
  ADD CONSTRAINT `FK_AB55E24FE9D3F622` FOREIGN KEY (`idp`) REFERENCES `user` (`id`);

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `FK_65D29B328789B572` FOREIGN KEY (`payment_instruction_id`) REFERENCES `payment_instructions` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_29A5EC27497DD634` FOREIGN KEY (`categorie`) REFERENCES `categorie` (`id`);

--
-- Constraints for table `reservation_petsitter`
--
ALTER TABLE `reservation_petsitter`
  ADD CONSTRAINT `FK_8471FEE562DCB42C` FOREIGN KEY (`idPetsitter`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_8471FEE5FE6E88D7` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`);

--
-- Constraints for table `reservation_veterinaire`
--
ALTER TABLE `reservation_veterinaire`
  ADD CONSTRAINT `FK_25B5BFE438AEB422` FOREIGN KEY (`id_veterinaire_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_25B5BFE479F37AE5` FOREIGN KEY (`id_user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `thread`
--
ALTER TABLE `thread`
  ADD CONSTRAINT `FK_31204C83B03A8386` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  ADD CONSTRAINT `FK_40A577C89D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_40A577C8E2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
