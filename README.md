# DOnate | Manipal

Vaibhav Verma

## Inspiration
Impactful giving should be easy and should be a streamlined process for those who are interested in making a difference. But arguably the most difficult part of this process is taking the first step and finding out just _which_ organization/fund aligns with the donor's ideals and how cost-effective and impactful this organization/fund is; donors should know that their dollars are truly making a difference in the field they are committed to.

## What it does
DOnate asks users to select causes they're interested in, which includes but is not limited to: animals, art, black-led, indigenous, justice, legal, music, research, etc. Based on these responses, DOnate generates a list of top organizations that are accepting donations– users can directly donate from here. 

Donors can return to DOnate and see their donor dashboard, which saves their "Causes" preference and past suggested organizations (the causes can be edited at any time, and new organizations will be generated), and tracks their historical donations in a graph. We want to make the donation process easy, so donors will come back and keep making a difference.

## How we built it
The initial design is on Figma. The front-end is built on React, the back-end on Springboot. We used MySQL for the database (of users, organizations, causes) and used the free charity API from Every.org. The graph that displays historical donations is built with Taipy. 

## Challenges we ran into
We could not find an API that truly encompassed what we wanted to do, which was to personalize the organization recommendations as much as possible with user preferences to not only causes, but to organization age, overall health, reviews, etc. The API from Every.org was free and easily accessible, so we used it for now.

We also ran into lots of setup problems at the start of our hacking. The time it took to set up Maven with our React/Springboot project cut into our programming time. We ended up chasing it back Sunday early morning.

Although members of our team have attended a few hackathons before, none of us have actually attempted to program a full application like DOnate in the past (we've solely done prototypes). So actually putting together all the components was a challenge in and of itself!

## Accomplishments that we're proud of
Again, since it was all of our first times actually coding a full program in the short time frame of a hackathon, we are very happy with the way it turned out (that it actually works!). We are proud that all the components we attempted (the UI design, user authentication, API call, Taipy graph) were successfully implemented.

## What we learned
How to work together effectively and efficiently with Github. Using the Every.org API. Using Taipy. 

## What's next for DOnate
Of course, we still have some bugs to fix (like recommended organizations showing two times on the page and minor UI tweaks). But other than that, we have bigger visions for DOnate:

We want to make the experience more personalized than currently (again, the APIs we had access to were extremely limited). We may move away from third-party APIs and use AI to generate the lists. 

We also want to gamify the experience a little more. The graph is a nice way to visualize the donor's progress and possibly incentive future donations, but we also want to implement a donation "streak" to further that.
