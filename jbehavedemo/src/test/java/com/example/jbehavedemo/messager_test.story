Given a messenger
When set server to facebook.pl
Then testing connection should return 0

When set server to google.com
Then testing connection should return 1

When set server to ps.ug.edu.pl
Then testing connection should return 0

When set server to inf.ug.edu
When set message to foobar
Then sending a message should return 1

When set server to fo
Then testing connection should return 1

When set server to wp.pl
When set message to foobar
Then sending a message should return 0

When set server to baz
When set message to bazfoo
Then sending a message should return 2

When set server to www.ug.pl
When set message to msg
Then sending a message should return 1

When set server to onet.pl
When set message to bu
Then sending a message should return 2

