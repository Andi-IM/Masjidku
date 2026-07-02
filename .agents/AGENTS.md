# Push to Production Rules

## BLOCKED — Direct Push to Production

You are STRICTLY PROHIBITED from running `git push` directly to the `production` branch.
All code changes intended for production MUST be pushed to a separate feature/refactor branch, and a Pull Request must be created. 

### EXCEPTION — Release Scripts

The ONLY exception to the above rule is when executing an automated release script (e.g., `npm run release`, `./gradlew release`, or a dedicated release workflow). In this specific context, the release script is allowed to update the `production` branch automatically. 

Do not bypass this rule under any circumstances during manual code refactoring or feature additions.
