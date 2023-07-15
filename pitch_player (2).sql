-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 15 juil. 2023 à 12:31
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pitch_player`
--

-- --------------------------------------------------------

--
-- Structure de la table `equipe`
--

CREATE TABLE `equipe` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `annee_fondation` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `match`
--

CREATE TABLE `match` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `match_type` varchar(255) DEFAULT NULL,
  `commentaire_match` varchar(255) DEFAULT NULL,
  `tournoi_id` int(11) DEFAULT NULL,
  `joueur_id` int(11) DEFAULT NULL,
  `terrain_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `sujet` varchar(255) DEFAULT NULL,
  `objet` varchar(255) DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `message_traitement` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `statut` tinyint(1) DEFAULT 0,
  `message` varchar(255) DEFAULT NULL,
  `owner_confirmation_status` varchar(255) DEFAULT NULL,
  `player_notification_status` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `terrain_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `resultatmatch`
--

CREATE TABLE `resultatmatch` (
  `id` int(11) NOT NULL,
  `match_id` int(11) DEFAULT NULL,
  `equipe1_id` int(11) DEFAULT NULL,
  `equipe2_id` int(11) DEFAULT NULL,
  `equipe1_score` int(11) DEFAULT NULL,
  `equipe2_score` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `terrain`
--

CREATE TABLE `terrain` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `longueur` float DEFAULT NULL,
  `largeur` float DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `disponibilite` tinyint(1) DEFAULT 1,
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `terrain`
--

INSERT INTO `terrain` (`id`, `nom`, `adresse`, `ville`, `longueur`, `largeur`, `owner_id`, `disponibilite`, `image`) VALUES
(4, 'Tayeb Mhiri', 'Sfax', NULL, 35, 35, NULL, 1, ''),
(7, 'Olampico', 'jedaida', NULL, 16, 17, NULL, 0, ''),
(9, 'ssssssssssss', 'ssssssssssssss', NULL, 17, 90, NULL, 0, 'C:\\Users\\Raouf\\Downloads\\nader.jpg'),
(12, 'alex', 'tunis', NULL, 18, 20, NULL, 0, ''),
(13, 'oppa', 'mestir', NULL, 18, 50, NULL, 0, ''),
(14, 'oppala', 'mestir', NULL, 19, 26, NULL, 0, ''),
(15, 'opppppp', 'mestir', NULL, 80, 60, NULL, 0, ''),
(16, 'azaerr', 'mestir', NULL, 60, 80, NULL, 0, ''),
(17, 'alexixxx', 'mannouba', NULL, 16, 50, NULL, 0, 'C:\\Users\\Raouf\\Desktop\\BRZ.png'),
(18, 'raouf', 'sfax', NULL, 50, 69, NULL, 0, 'C:\\Users\\Raouf\\Desktop\\SRB.png'),
(19, 'wijden', 'bardo', NULL, 55, 65, NULL, 0, 'C:\\Users\\Raouf\\Desktop\\SUI.png'),
(24, 'aaaaaaaaaaaa', 'gabes', NULL, 15, 26, NULL, 0, 'C:\\Users\\Raouf\\Desktop\\148856643_2854390924783514_8450040186411394195_o.jpg'),
(26, 'grand', 'sousse', NULL, 80, 80, NULL, 0, NULL),
(28, 'vvvv', 'rz', NULL, 96, 50, NULL, 0, NULL),
(32, 'grandsssssss', 'fvrfrrrvfr', NULL, 25, 99, NULL, 0, 'C:\\Users\\Raouf\\Downloads\\raouf.jpg'),
(34, 'hhhfs', 'sfh', NULL, 12, 13, NULL, 1, NULL),
(46, 'zezeezezezeze', 'ezzezezeezeeze', NULL, 99, 99, NULL, 1, NULL),
(47, 'attttttttttttttttttttt', 'ttttttttttttttttttttttt', NULL, 88, 88, NULL, 1, 'file://C:/Users/Raouf/AppData/Local/Temp/temp2163451706074822023.png'),
(48, 'rrrrrrrrrrrrrrrrrrr', 'rrrrrrrrrrrrrrrrrrr', NULL, 99, 99, NULL, 1, 'file://C:/Users/Raouf/AppData/Local/Temp/temp8114778015517866115.png'),
(49, 'oooooooooooooo', 'oooooooooooooooo', NULL, 99, 99, NULL, 1, 'file://C:/Users/Raouf/AppData/Local/Temp/temp6085267726349789763.png'),
(50, 'zab', 'zab', NULL, 88, 88, NULL, 1, 'file://C:/Users/Raouf/AppData/Local/Temp/temp6373145628364806720.png'),
(51, 'sqsss', 'qqssqs', NULL, 99, 99, NULL, 1, 'file://C:/Users/Raouf/AppData/Local/Temp/temp3238042749108759694.png');

-- --------------------------------------------------------

--
-- Structure de la table `tournoi`
--

CREATE TABLE `tournoi` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `nbr_participant` int(11) DEFAULT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `equipe_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `match`
--
ALTER TABLE `match`
  ADD PRIMARY KEY (`id`),
  ADD KEY `joueur_id` (`joueur_id`),
  ADD KEY `terrain_id` (`terrain_id`),
  ADD KEY `tournoi_id` (`tournoi_id`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `terrain_id` (`terrain_id`);

--
-- Index pour la table `resultatmatch`
--
ALTER TABLE `resultatmatch`
  ADD PRIMARY KEY (`id`),
  ADD KEY `match_id` (`match_id`),
  ADD KEY `equipe1_id` (`equipe1_id`),
  ADD KEY `equipe2_id` (`equipe2_id`);

--
-- Index pour la table `terrain`
--
ALTER TABLE `terrain`
  ADD PRIMARY KEY (`id`),
  ADD KEY `owner_id` (`owner_id`);

--
-- Index pour la table `tournoi`
--
ALTER TABLE `tournoi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `owner_id` (`owner_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `equipe_id` (`equipe_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `equipe`
--
ALTER TABLE `equipe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `match`
--
ALTER TABLE `match`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `resultatmatch`
--
ALTER TABLE `resultatmatch`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `terrain`
--
ALTER TABLE `terrain`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT pour la table `tournoi`
--
ALTER TABLE `tournoi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `match`
--
ALTER TABLE `match`
  ADD CONSTRAINT `match_ibfk_1` FOREIGN KEY (`joueur_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `match_ibfk_2` FOREIGN KEY (`terrain_id`) REFERENCES `terrain` (`id`),
  ADD CONSTRAINT `match_ibfk_3` FOREIGN KEY (`tournoi_id`) REFERENCES `tournoi` (`id`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `reclamation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`terrain_id`) REFERENCES `terrain` (`id`);

--
-- Contraintes pour la table `resultatmatch`
--
ALTER TABLE `resultatmatch`
  ADD CONSTRAINT `resultatmatch_ibfk_1` FOREIGN KEY (`match_id`) REFERENCES `match` (`id`),
  ADD CONSTRAINT `resultatmatch_ibfk_2` FOREIGN KEY (`equipe1_id`) REFERENCES `equipe` (`id`),
  ADD CONSTRAINT `resultatmatch_ibfk_3` FOREIGN KEY (`equipe2_id`) REFERENCES `equipe` (`id`);

--
-- Contraintes pour la table `terrain`
--
ALTER TABLE `terrain`
  ADD CONSTRAINT `terrain_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `tournoi`
--
ALTER TABLE `tournoi`
  ADD CONSTRAINT `tournoi_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
