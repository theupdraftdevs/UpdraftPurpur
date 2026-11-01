# Updraft

**Updraft** is the server software maintained by Updraft-Network. It is built on Paper and Purpur and aims to provide a fast, reliable, and configurable Minecraft server for network operators.

[![License](https://img.shields.io/github/license/theupdraftdevs/UpdraftPurpur)](LICENSE)
[![Build](https://github.com/theupdraftdevs/UpdraftPurpur/actions/workflows/build.yml/badge.svg)](https://github.com/theupdraftdevs/UpdraftPurpur/actions/workflows/build.yml)

## Features

Updraft retains upstream Paper and Purpur APIs and gameplay features, with Updraft-specific improvements developed for Updraft-Network. Purpur's package names, API coordinates, settings file, and command remain available for compatibility with existing plugins and server setups.

## Building

Use JDK 25 and clone this repository. In the repository root, run:

```sh
./gradlew applyAllPatches
./gradlew build
```

To create the runnable server jar, run:

```sh
./gradlew createPaperclipJar
```

The Paperclip jar is written to `purpur-server/build/libs`. See [CONTRIBUTING.md](CONTRIBUTING.md) for patch workflow details.

## Upstream projects

Updraft builds on [Paper](https://github.com/PaperMC/Paper) and [Purpur](https://github.com/PurpurMC/Purpur). Their upstream contributions and licenses remain attributed to their respective projects. This repository is licensed under the MIT License; see [LICENSE](LICENSE).
