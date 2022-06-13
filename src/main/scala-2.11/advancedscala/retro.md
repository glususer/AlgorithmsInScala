# Retro 18OCT2021

### Action Points

#### Carried Over:
- **Problem** We don’t have any routine platform to share technical doubt/suggestions.
  - **Action Point**: Setup groups of 3-5 people who meet on their own, and then a common meeting later where we go through the ideas.
    - **Who** Björn & Anuj
    - **When** Next Retro.

- **Problem** Sometimes issues that are not part of a radar can be found during development/integration which lead to the radars being worked on much longer than anticipated. This can lead to confusion regarding what is left to do and makes handovers more difficult.
    - **Action Point**: When a new issue arises either update the existing radar to mention the new development, file a radar and get the existing one merged OR file a new radar and block the existing radar on the new one so the radars don't get too large and stay in the sprint for too long.
        - **Who** Whole Team.
        - **When** Next Retro

- **Problem** Changelog bot for gemini-checks isn't working.
    - **Action Point**: Fix it.
        - **Who** Whole Team.
        - **When** Next Retro.

- **Problem** Lack of communication with LL leading to longer time to get checks to integrate.
    - **Action Point**: If it’s difficult to book a meeting with a LL due to the time difference then describe your problem to your SME group and ask someone there to have the LL discussion.
        - **Who** Whole Team.
        - **When** Next Retro.
        
- **Problem** Knowledge Sharing sessions are really good. Could we consider having one each sprint, maybe for a smaller duration. Sometimes the new check writers don’t know about the different things there are to know, so its good having a session where someone more experienced about a particular topic explaining the things.
    - **Action Point**: .
        - **Who** Lars.
        - **When** Next Retro.
        
- **Problem** Ghost meetings showing up.
    - **Action Point**: Only have actually existing meetings in invites, and decline meeting which you don't plan on attending.
        - **Who** Meeting organisers, meeting attendees.
        - **When** Next Retro.
        
- **Problem** Working on check conversions when nothing in Bot.
    - **Action Point**: Announce in channel and see if something is going to be ready for review else you can pick check conversion.
        - **Who** Whole Team.
        - **When** Next Retro.

#### New:
- **Problem** Ops knowledge is very limited to handful of people.
    - **Action Point**: Spread Ops knowledge to more people.
        - **Who** It is upto individual CW to ask/understand the process.
        - **When** Next Retro

- **Problem** People don't read check-dev and other slack channels and may miss crucial information about radars that may lead to redundant work. (If possible try to include the information about checks in the radar as well).
    - **Action Point**: Do read slack channels especially the check-dev, check-announce, check-qna.
        - **Who** Whole Team.
        - **When** Next Retro.

- **Problem** One Autofix that were supposed to run in maintenance were not back ported which means that it hadn’t run in production as it should before we run it over maintenance.
    - **Action Point**: Be sure to follow the 101 and back port the Autofix when you are done with it.
        - **Who** People working on Autofixes.
        - **When** Next Retro.

- **Problem** PR titles should always include radar ID if it exists
    - **Action Point**: Don't merge a PR without a proper title
        - **Who** Whole Team.
        - **When** Next Retro.

- **Problem** Multiple checks having the same modifications (i.e. adding a reflist column) in the same sprint should be picked up by the same person.
    - **Action Point**: It helps when the LL has one point of contact for datasets , reviews and one or max two people if there are a lot of them.
        - **Who** Whole Team.
        - **When** Next Retro.

- **Problem** I have seen som Radars being picked up for review and then put back on dataeval. There are valid reasons for this, but we shouldn’t put them back because we don’t know how to review it.
    - **Action Point**: If you don’t know how something works in the check, ask someone to pair review it with you.
        - **Who** Whole Team.
        - **When** Next Retro.

- **Problem** Checks with an autofix does not have autofix ID populated in check metadata
    - **Action Point**: Add autofix ID. We riks to integrate checks with outdated Autofixes if this is missed.
        - **Who** People working on Autofixes.
        - **When** Next Retro.
        
- **Problem**  We should not hot fix during RC if we don’t really have to do it.
    - **Action Point**: Be sure that you can finish the hot fix before the RC or wait until after.
        - **Who** Whole Team.
        - **When** Next Retro.
    

#### Not discussed this time:
-NIL

### Nice box:
- We are better at picking up large Radars in the beginning of the sprint.

### Retro-retro:
- NIL