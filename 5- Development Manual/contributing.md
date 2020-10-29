# Contributing to This Project
***Thanks for your interest in contributing to our project! Please read the guidelines below for adding or reviewing code before proceeding.***

---

## Adding Code
**If you wish to add features or correct bugs in this project's code, please follow these instructions:**
1. First, check out our [ZenHub board](https://github.com/WaterbendingToph/cs414-f20-public_class_TeamD/wiki/_new#workspaces/cs414-f20-public-class-teamd-5f515ce4b0b1cb001220e5f7/board?repos=292679509) or [Issues page](https://github.com/WaterbendingToph/cs414-f20-public_class_TeamD/issues) to see if an issue exists that addresses your concern.
2. If you have a new issue you'd like to add, contact the repository administrator (currently Dakota Golighty) to gain write access to the repository.
3. Once you have access to the repository, create a new issue on our ZenHub board.
    - Issues should include a brief description of the problem being addressed or feature being added.
    - Issues should also include the *task* label, a complexity *estimate*, and the proper *milestone*/*epic* identifiers.
    - If you plan to work on the issue immediately, please *assign yourself* to the issue and place it in the *Doing* pipeline.
    - Well-documented issues also often include a checklist of tasks that allow for clear documention of completion progress, as well as clarity during review.
4. Clone the repository via the instructions in the ***Installation*** section of the [README](https://github.com/WaterbendingToph/cs414-f20-public_class_TeamD/blob/master/README.md) file.
5. Checkout a new branch with an up-to-date version of the master branch.
6. In the console, navigate to the the project's root directory ("*cs414-f20-public_class_TeamD*"). From this directory, you may load any dependencies that have not already been added to your local environment by running `npm i`.
    - **Complete this step even if you believe you already have all dependencies**, as new ones are added regularly.
7. Once dependencies have been loaded, make your edits on your new branch using your IDE of choice.
    - This project has been designed to allow development on multiple IDEs. To date, project members have used VSCode, IntelliJ IDEA, Eclipse, and Sublime Text for development without issues. Git *should* ignore any files that are IDE-specific, but if you encounter an issue with a new IDE, please contact a team member or open an issue.
    - Please keep in mind that files *outside* the */src* folder are used exclusively for the CS414 Fall 2020 semester project deliverables. As such, **these files should not be edited unless with express permission of the public_class_TeamD project team**.
8. When you are happy with your changes, test them by completing the ***Testing*** section below.
9. If your code has passed all tests, submit a Pull Request to have the changes included in master.
    - Feel free to select a team member to review your request. **All PRs must include at least one review by someone other than the submitter before being merged**.
    - Pull Requests should include a brief description of the issue being solved, as well as any notes that the reviewer may require. Ensure you connect your Pull Request to the appropriate issue.
10. A reviewer will review your code. You will be notified if your code requires changes or if the reviewer has questions before approving the merge. Periodically review the Pull Request's *Conversation* tab for any comments or questions about your request.
11. Once a reviewer has approved your changes, they will merge your code. Only a reviewer should merge code, and a submitter should **never** merge their own code into master.
12. That's it! Thanks for contributing!

---

## Reviewing Code
**If you have been asked to review code for changes made to this project, please follow these instructions:**
1. First, review the Issue card and Pull Request for the issue you'd like to review, to ensure that you are sufficiently versed in the project to provide a constructive review. 
2. Clone the repository via the instructions in the ***Installation*** section of the [README](https://github.com/WaterbendingToph/cs414-f20-public_class_TeamD/blob/master/README.md) file.
3. Checkout the branch associated with the Pull Request you are reviewing.
4. In the console, navigate to the the project's root directory ("*cs414-f20-public_class_TeamD*"). From this directory, you may load any dependencies that have not already been added to your local environment by running `npm i`.
    - **Complete this step even if you believe you already have all dependencies**, as new ones are added regularly.
5. Once dependencies have been loaded, test the code using the ***Testing*** section below.
6. If the code passes all tests, review each edited file in GitHub.
    - Navigate to the Pull Request for this change and click the *Files Changed* tab to view all changed files.
    - Review each changed file.
    - Focus first on whether the code accomplishes the stated objective from the Pull Request.
    - Then, focus on test coverage, adherance to applicable style guides (if any are in place at the time), and code cleanliness.
    - Once you are satisfied with the state of a given file, click the *Viewed* checkbox to confirm that you have reviewed that file and collapse it.
7. After reviewing all changed files, submit your review by clicking the *Review changes* link. 
    - If the Pull Request is ready to be merged, click the *Approve* button.
    - If changes need to be made, or you have questions before approving the change, select the appropriate button and then click *Submit review*.
8. If a Pull Request has been approved, the person submitting the approval may then merge the request with the master branch.
    - In the Pull Request, navigate to the *Conversation* tab.
    - Near the bottom of the feed, ensure that the Pull Request is connected to the appropriate issue.
    - Click the *Merge pull request* button to merge this request with the master branch.
    - Once merged, delete the associated branch.
9. That's it! Thanks for contributing!

---

## Testing
***This project uses a combination of automated and user-driven testing. Most changes will require a combination of both.*** 

### Automated Testing
Automated testing is accomplished at compile-time via [JUnit 5](https://junit.org/junit5/) for all server-side code and [Jest](https://jestjs.io/) for all client-side code. These test files can be found in the [`/src/test`](https://github.com/WaterbendingToph/cs414-f20-public_class_TeamD/tree/master/src/test/java/cs414f20/teamd) and [`src/main/frontend/src`](https://github.com/WaterbendingToph/cs414-f20-public_class_TeamD/tree/master/src/main/frontend/src) folders, respectively. 

When adding code, please ensure that tests are written to maintain coverage over any new features. For example, if you are adding code to produce an error message in `Login.java` if a user tries to login with an invalid username, please include a new automated test in `LoginTest.java` to account for this new feature (preferably multiple tests to check for a valid and invalid username).

When reviewing code, ensure that all automated tests are passed by packaging the files via the ***Running the Server*** instructions below. Failed tests will prevent the code from being packaged and should be addressed by the submitter before approval.

### User-Driven Testing
This testing involves user interaction with the front end websites and generally cannot be automated. Tests that fall into this category typically involve user experience modifications, such as moving buttons to new locations or adding a new feature. These modifications require the reviewer to interact with the front-end components to ensure that they work as the submitter intended. This requires diligence by both the submitter (in ensuring that the Issue/Pull Request have sufficient detail) and the reviewer (in taking the time to thoroughly test all features).

While this type of testing is difficult to encapsulate, some general guidelines can guide the reviewer. To illustrate these guidelines, we will use the example of adding the ability to move a bishop chess piece:

1. **Test the intended outcome of the change:** Move the bishop in a diagonal fashion to different legal positions and ensure the move was processed properly.
2. **Test several invalid outcomes of the change to ensure errors are produced:** Move the bishop in several illegal patterns to ensure that none are allowed to occur (either by automatically moving the piece back to its original location, producing a visible error message, etc.) Examples of invalid moves would be jumping another piece, moving off the board, or moving in a non-diagonal fashion.
3. **Test other components on the page to ensure they still work:** After completing a move, attempt to use the browser Back button to ensure the move persists. Navigate to other pages and return to this page. Test menus and other page components to ensure they still behave properly.

Other tests can certainly be performed, but at a minimum, ***a reviewer should strive to test intended outcomes, unintended outcomes, and continued operation of other components***.

---
## Running the Server
### Running the Front End
Since this project uses [React](https://reactjs.org/) as a front end framework, you may use the "hot loader" feature to continuously test changes to front end components. To do this, you simply have to start the front end server and load it in a browser:
1. In the console, navigate to the [`src/main/frontend`](https://github.com/WaterbendingToph/cs414-f20-public_class_TeamD/tree/master/src/main/frontend) directory. You should see the `package.json` file here.
2. Run `npm start` to start the app. 
3. After starting up, the script should automatically open the web app in a new browser window. However, if this does not load, you may navigate to http://localhost:3000 to view the app.
4. As you make changes to the front end, they will automatically be updated in this browser window each time you save the changed file.

### Running the Back End

***Since the database used in this project is stored on Colorado State University (CSU) Computer Science lab machines, the project is only available to users who have access to these machines. While the development server is not hosted on these machines, the database connections rely on access to them, and thus the Java files will not compile properly if you are not securely connected to one of these machines.***

To load the back end server, you must first securely connect to the CSU system. Using PowerShell (Windows) or the terminal (Mac/Linux), connect to the system via the following command:

>ssh -L 56247:faure.cs.colostate.edu:3306 <eid-username>@<machine-name>.cs.colostate.edu

replacing `<eid-username>` with your EID and `<machine-name>` with the identifier of your preferred lab machine. Once connected, you can perform the steps below to access the server. **You must remain connected to the CSU machine throughout development to maintain access to the back end server.**

This project uses [Apache Maven](https://maven.apache.org/) and [Spring Boot](https://spring.io/projects/spring-boot) as a project manager and back end framework, respectively. This allows for each packaging of builds, as well as testing of the back end components through a micro service. To test the back end, you must use these tools in tandem:

1. First, you must package the project. To do this from the console, navigate to the root directory for the project ("*cs414-f20-public_class_TeamD*"). Then, run `mvn package` to compile and package the project. 
2. Once the project is prepared, you can then run `mvn spring-boot:run` from the same directory to load the back end server.
3. While React automatically loads a browser window, the back end server does not. To view the server, navigate a browser window to http://localhost:8080.
4. To view changes in back end files or database connections, the project must be rebuilt using `mvn package` and then reloaded using `mvn spring-boot:run`. Changes are not hot-loaded into the browser like they are with React, since Java files must be recompiled with each change. 

### Notes on Running the Server
While front end changes can be made quickly with the hot loader feature, any changes that require communication with the back end will require a repackaging/reloading of the project. Because of this, we recommend loading the front end and back end servers simultaneously and using port 3000 to observe front end edits and only updating the back end when required.