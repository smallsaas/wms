import React from 'react';
import DetailsTemplate from '@/components/Details/DetailsTemplate';
import setting from './config/appPermission-setting';
    
export default () => {

  return <DetailsTemplate
    namespace="appPermission_view"
    setting={setting}
    config={setting.viewConfig}
  />
}
    
