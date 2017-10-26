INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934216708099', 'Account模块', 'Account.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934195736578', '920934934216708099', '查看Account', 'Account.view'),
('920934934216708097', '920934934216708099', '更新Account', 'Account.update'),
('920934934216708098', '920934934216708099', '删除Account', 'Account.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096708', 'ApplicantPayment模块', 'ApplicantPayment.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096705', '920934934225096708', '查看ApplicantPayment', 'ApplicantPayment.view'),
('920934934225096706', '920934934225096708', '更新ApplicantPayment', 'ApplicantPayment.update'),
('920934934225096707', '920934934225096708', '删除ApplicantPayment', 'ApplicantPayment.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934216708103', 'Applicant模块', 'Applicant.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934216708100', '920934934216708103', '查看Applicant', 'Applicant.view'),
('920934934216708101', '920934934216708103', '更新Applicant', 'Applicant.update'),
('920934934216708102', '920934934216708103', '删除Applicant', 'Applicant.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096712', 'ApplicantSnapshot模块', 'ApplicantSnapshot.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096709', '920934934225096712', '查看ApplicantSnapshot', 'ApplicantSnapshot.view'),
('920934934225096710', '920934934225096712', '更新ApplicantSnapshot', 'ApplicantSnapshot.update'),
('920934934225096711', '920934934225096712', '删除ApplicantSnapshot', 'ApplicantSnapshot.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096720', 'ApplicationHistory模块', 'ApplicationHistory.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096717', '920934934225096720', '查看ApplicationHistory', 'ApplicationHistory.view'),
('920934934225096718', '920934934225096720', '更新ApplicationHistory', 'ApplicationHistory.update'),
('920934934225096719', '920934934225096720', '删除ApplicationHistory', 'ApplicationHistory.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096716', 'Application模块', 'Application.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096713', '920934934225096716', '查看Application', 'Application.view'),
('920934934225096714', '920934934225096716', '更新Application', 'Application.update'),
('920934934225096715', '920934934225096716', '删除Application', 'Application.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096752', 'Contact模块', 'Contact.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096749', '920934934225096752', '查看Contact', 'Contact.view'),
('920934934225096750', '920934934225096752', '更新Contact', 'Contact.update'),
('920934934225096751', '920934934225096752', '删除Contact', 'Contact.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096728', 'CustomerAttachment模块', 'CustomerAttachment.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096725', '920934934225096728', '查看CustomerAttachment', 'CustomerAttachment.view'),
('920934934225096726', '920934934225096728', '更新CustomerAttachment', 'CustomerAttachment.update'),
('920934934225096727', '920934934225096728', '删除CustomerAttachment', 'CustomerAttachment.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096732', 'CustomerEmploy模块', 'CustomerEmploy.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096729', '920934934225096732', '查看CustomerEmploy', 'CustomerEmploy.view'),
('920934934225096730', '920934934225096732', '更新CustomerEmploy', 'CustomerEmploy.update'),
('920934934225096731', '920934934225096732', '删除CustomerEmploy', 'CustomerEmploy.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096724', 'Customer模块', 'Customer.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096721', '920934934225096724', '查看Customer', 'Customer.view'),
('920934934225096722', '920934934225096724', '更新Customer', 'Customer.update'),
('920934934225096723', '920934934225096724', '删除Customer', 'Customer.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096736', 'CustomerSnapshot模块', 'CustomerSnapshot.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096733', '920934934225096736', '查看CustomerSnapshot', 'CustomerSnapshot.view'),
('920934934225096734', '920934934225096736', '更新CustomerSnapshot', 'CustomerSnapshot.update'),
('920934934225096735', '920934934225096736', '删除CustomerSnapshot', 'CustomerSnapshot.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096756', 'Enterpise模块', 'Enterpise.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096753', '920934934225096756', '查看Enterpise', 'Enterpise.view'),
('920934934225096754', '920934934225096756', '更新Enterpise', 'Enterpise.update'),
('920934934225096755', '920934934225096756', '删除Enterpise', 'Enterpise.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096740', 'Job模块', 'Job.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096737', '920934934225096740', '查看Job', 'Job.view'),
('920934934225096738', '920934934225096740', '更新Job', 'Job.update'),
('920934934225096739', '920934934225096740', '删除Job', 'Job.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096744', 'JobPlan模块', 'JobPlan.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096741', '920934934225096744', '查看JobPlan', 'JobPlan.view'),
('920934934225096742', '920934934225096744', '更新JobPlan', 'JobPlan.update'),
('920934934225096743', '920934934225096744', '删除JobPlan', 'JobPlan.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096748', 'JobPlanType模块', 'JobPlanType.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096745', '920934934225096748', '查看JobPlanType', 'JobPlanType.view'),
('920934934225096746', '920934934225096748', '更新JobPlanType', 'JobPlanType.update'),
('920934934225096747', '920934934225096748', '删除JobPlanType', 'JobPlanType.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934225096760', 'Profile模块', 'Profile.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934225096757', '920934934225096760', '查看Profile', 'Profile.view'),
('920934934225096758', '920934934225096760', '更新Profile', 'Profile.update'),
('920934934225096759', '920934934225096760', '删除Profile', 'Profile.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('920934934262845444', 'Resume模块', 'Resume.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('920934934262845441', '920934934262845444', '查看Resume', 'Resume.view'),
('920934934262845442', '920934934262845444', '更新Resume', 'Resume.update'),
('920934934262845443', '920934934262845444', '删除Resume', 'Resume.delete');
