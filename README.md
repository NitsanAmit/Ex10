"I pledge the highest level of ethical principles in support of academic excellence.  I ensure that all of my work reflects my own abilities and not those of someone else."

## Answer to theoretical question:
>**As a client, at the first time you got a token. You have 2 options:**
 a. save the token locally (for example in a file or in SP)
 b. use this token only in this current app-launch, and re-request a token from the server each time the app launches again.
 In your git repository inside the README file you should write at least 1 pro and 1 con for each implementation (a) & (b).

### Pros of a:
1. Fast, no need to communicate with the server many times.
2. Saves mobile data for the user.

### Cons of a:
1. Somewhat unsafe, the token might be stolen by other malicious software on the user's phone and abuse it.
2. Still need to check with the server if the token is expired.

### Pros of b:
1. Always promised that the token is fresh (valid)

### Cons of b:
1. Might be slow, depending on the server availability and user network connection.
