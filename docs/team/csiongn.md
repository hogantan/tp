# Ng Choon Siong - Project Portfolio

## PROJECT: Reeve

Reeve is a desktop companion application for one-on-one private tutors designed to help them better manage their students' academic and administrative needs.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

## Summary of Contributions

* **Major enhancement implemented**: 
  * Developed the **notebook** feature of **Reeve**.

    * What it does: **Reeve's notebook** allows tutors to keep track of notes containing small amounts of information unrelated to students 
    * Justification: This feature is important for a private tutor as there are times when there is information relating to tutoring that needs to be stored but it is not related to any student. This helps to make Reeve a one stop location for storing all kinds of information.
* **Other enhancements implemented**:
    * **Created `sort` command** to allow sorting of the student list by name, class time or year of study. ([\#112](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/112))
        * Justification: This feature is important as it allows private tutors to navigate through the list of students better depending on the needs of the tutor.
    * **Enhanced `find` command** to allow searching by multiple criteria and **added support** for searching by name, school and year. ([\#60](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/60))
* **Code contribution**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=csiongn&tabRepo=AY2021S1-CS2103T-W15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)
* **Other contributions**:
  
  * **Substantially reworked the structure of `Year` class** for better input validation and management. ([\#96](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/96))
  * Helped to **rename Address Book references to Reeve** ([\#67](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/67))
* **Project management**:
* Reminded team of deliverables.

* **Documentation**:
    * User Guide:
        * Updated documentation for the command `find` ([\#38](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/38) and [\#60](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/60))
        * Added documentation for the command `sort` ([\#130](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/130))
        * Added documentation for notebook feature ([TBA]())
    * Developer Guide:
        * Added implementation details of the `find` ([TBA]()), `sort` ([TBA]())
* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#194](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/194)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/csiongn/ped/issues/1), [2](https://github.com/csiongn/ped/issues/2), [3](https://github.com/csiongn/ped/issues/3))
