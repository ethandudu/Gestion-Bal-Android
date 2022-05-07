-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : sam. 07 mai 2022 à 10:16
-- Version du serveur :  10.3.34-MariaDB-0ubuntu0.20.04.1
-- Version de PHP : 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bal`
--

-- --------------------------------------------------------

--
-- Structure de la table `bugs`
--

CREATE TABLE `bugs` (
  `id` int(11) NOT NULL,
  `date` text NOT NULL,
  `user` text NOT NULL,
  `ex` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `bugs`
--

-- --------------------------------------------------------

--
-- Structure de la table `cloak`
--

CREATE TABLE `cloak` (
  `ID` int(11) NOT NULL,
  `Hash` text NOT NULL,
  `Num` int(11) NOT NULL,
  `take` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `members`
--

CREATE TABLE `members` (
  `ID` int(11) NOT NULL,
  `user` text NOT NULL,
  `passwd` text NOT NULL,
  `name` text NOT NULL,
  `firstname` text NOT NULL,
  `classe` text NOT NULL,
  `mail` text NOT NULL,
  `admin` text NOT NULL DEFAULT 'Non',
  `addby` text NOT NULL,
  `validation` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `members`
--

INSERT INTO `members` (`ID`, `user`, `passwd`, `name`, `firstname`, `classe`, `mail`, `admin`, `addby`, `validation`) VALUES
(1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Istrateur', 'Admin', 'TG 1', 'mail@gmail.com', 'Oui', 'Admin Istrateur', 'Oui');

-- --------------------------------------------------------

--
-- Structure de la table `participants`
--

CREATE TABLE `participants` (
  `ID` int(11) NOT NULL,
  `name` text NOT NULL,
  `firstname` text NOT NULL,
  `classe` text NOT NULL,
  `age` text NOT NULL,
  `birthdate` text NOT NULL,
  `minor` text DEFAULT NULL,
  `autorisation` text DEFAULT NULL,
  `payed` text NOT NULL,
  `mail` text NOT NULL,
  `tel` text NOT NULL,
  `urgence` text DEFAULT NULL,
  `registrationDate` text NOT NULL,
  `validated` text NOT NULL DEFAULT 'Non',
  `addby` text NOT NULL,
  `validby` text NOT NULL DEFAULT 'Non',
  `hash` text NOT NULL,
  `cancel` text NOT NULL DEFAULT 'False',
  `cancelby` text NOT NULL DEFAULT 'Non'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `participants`
--


-- --------------------------------------------------------

--
-- Structure de la table `students`
--

CREATE TABLE `students` (
  `ID` int(11) NOT NULL,
  `name` text NOT NULL,
  `firstname` text NOT NULL,
  `birth` text NOT NULL,
  `class` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `students`
--


--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bugs`
--
ALTER TABLE `bugs`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `cloak`
--
ALTER TABLE `cloak`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `participants`
--
ALTER TABLE `participants`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bugs`
--
ALTER TABLE `bugs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `cloak`
--
ALTER TABLE `cloak`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `members`
--
ALTER TABLE `members`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `participants`
--
ALTER TABLE `participants`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `students`
--
ALTER TABLE `students`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=340;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
