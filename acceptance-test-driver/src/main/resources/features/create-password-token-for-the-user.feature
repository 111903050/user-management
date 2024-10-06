Feature: Create Password Token for the user

  Rule: Create password token for the user
    Example: Create password token if email and phone number are linked
      Given Below is the user with the email "vishusw24@gmail.com"
        | Employee Id    | First Name   | Phone Number | E-mail              | Hire Date   | Birth Date  |
        | EMP0001        | Vishu        | 8263002563   | vishusw24@gmail.com |  2024-12-02 |  2001-04-11 |
      When User has given the email "vishusw24@gmail.com" and phone number "8263002563" as follows.
      Then Create a password for user
        | UserName            | Password          | Expiry Date         |
        | vishusw24@gmail.com | password          | 2001-04-11T00:00:00 |

    Example: Do not create password token if email and phone number are not linked
      Given Below is the user with the email "vishusw24@gmail.com"
        | Employee Id    | First Name   | Phone Number | E-mail              | Hire Date   | Birth Date  |
        | EMP0001        | Vishu        | 8263002563   | vishusw24@gmail.com |  2024-12-02 |  2001-04-11 |
      When User has given the email "vishusw24@gmail.com" and phone number "9292002563" as follows.
      Then Do not Create a password for user
        | UserName            | Password          | Expiry Date         |

    Example: Do not create password token if no user exits for a given email
      Given Below is the user with the email "vishusw24@gmail.com"
        | Employee Id    | First Name   | Phone Number | E-mail              | Hire Date   | Birth Date  |
      When User has given the email "vishusw24@gmail.com" and phone number "9292002563" as follows.
      Then Do not Create a password for user
        | UserName            | Password          | Expiry Date         |