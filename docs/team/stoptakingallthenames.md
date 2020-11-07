---
layout: page
title: Ong Ying Gao's Project Portfolio Page
---

## Project: Reeve

Reeve is a desktop companion application for one-on-one private tutors designed to help them better manage their students' academic and administrative needs.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Feature Addition**: Added the ability to record and manage questions from students.
  * What it does: Allows the user to log questions from students, resolve them and delete them when no longer needed.
  * Justification: This helps tutors significantly as it allows them to keep track of questions from their students and gives them advanced notice to resolve them.
    Furthermore, the ability to resolve it with a solution allows tutors to use the solution to answer future similar questions from students.
  * Highlights: Since this is a new feature with its own set of commands, the implementation required modifying almost every module. Some existing commands had to be altered as well.
    Almost all new functional code had accompanying test cases, resulting in 1697 new lines of code with 84.50% coverage.

* **Feature Addition**: Added the ability to store tuition fee payment data and find all students with overdue fees.
  * Highlights: In order to implement monitoring of payment dates, I had to create a more manual approach to parsing strings into dates as the `parse()` method in the LocalDate API was too lenient.

* **Code contribution**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=stoptakingallthenames)

* **Project management**:
  * Managed releases `v1.2` and `v1.3` (2 releases) on GitHub.
  * Set up Github repo and issue tracker.
  * Maintained issue tracker.

* **Enhancements to existing features**:
  * Wrote additional tests for existing and new features to increase coverage ([\#75](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/75)).
  * Enhanced code quality to ensure adherence to abstraction levels, improve defensiveness and reduce coupling ([\#103](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/103)
    and [\#194](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/194)).
  * Added utility and classes methods to minimise code duplication ([\#211](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/211) and [\#213](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/213)).

* **Documentation**:
  * User Guide:
    * Documented the `overdue` ([\#79](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/79)) and `question` ([\#71](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/103)) features.
    * Did cosmetic tweaks to existing documentation of features: ([\#140](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/140) and [\#212](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/194)).
  * Developer Guide:
    * Added implementation details of the `overdue` ([\#105](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/105)) and `question` ([\#103](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/103))
      features.
    * Included sequence and activity diagrams for the above features.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#60](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/60), [\#62](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/62),
    [\#68](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/68), [\#214](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/214)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/StopTakingAllTheNames/ped/issues/1),
    [2](https://github.com/StopTakingAllTheNames/ped/issues/5), [3](https://github.com/StopTakingAllTheNames/ped/issues/2))
