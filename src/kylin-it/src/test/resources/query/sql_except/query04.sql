--
-- Licensed to the Apache Software Foundation (ASF) under one
-- or more contributor license agreements.  See the NOTICE file
-- distributed with this work for additional information
-- regarding copyright ownership.  The ASF licenses this file
-- to you under the Apache License, Version 2.0 (the
-- "License"); you may not use this file except in compliance
-- with the License.  You may obtain a copy of the License at
--
--     http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--
select * from (
(select sum(ACCOUNT_BUYER_LEVEL), ACCOUNT_SELLER_LEVEL
from TEST_ACCOUNT
where ACCOUNT_COUNTRY='CN'
group by ACCOUNT_SELLER_LEVEL
)
EXCEPT
(select sum(ACCOUNT_SELLER_LEVEL), ACCOUNT_BUYER_LEVEL
from TEST_ACCOUNT
where ACCOUNT_COUNTRY='FR'
group by ACCOUNT_BUYER_LEVEL
)
)