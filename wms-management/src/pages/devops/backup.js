import React from 'react';

import Backup from '@/pages/devops/config/Backup';

import DiffCompose from './config/Backup/diffCompose';

export default (props) => (
    <>
        <div style={{marginBottom:'16px'}}>
            <DiffCompose/>
        </div>
        <Backup />
    </>
);
