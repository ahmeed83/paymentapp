import React, { useContext } from 'react';
import { List, Avatar, Icon, Spin } from 'antd';
import { Link } from 'react-router-dom';
import { AccountContext } from '../contexts/AccountContext';

export const AccountListPage = () => {
  const { accounts, loading } = useContext(AccountContext);

  const getIndicatorIcon = () => (
    <Icon type='loading' className='spinner' spin />
  );

  return (
    <div>
      {loading ? (
        <Spin indicator={getIndicatorIcon()} />
      ) : (
        <div>
          <h1 className='pt-5'>Account List Page</h1>

          <List
            itemLayout='horizontal'
            dataSource={accounts}
            renderItem={item => (
              <List.Item className='pt-5'>
                <List.Item.Meta
                  avatar={
                    <Avatar
                      style={{ backgroundColor: '#87d068' }}
                      icon='user'
                    />
                  }
                  title={<Link to={`/transaction/${item.id}`}> {item.name}</Link>}
                  description={item.email}
                />
                <div>${item.balance}</div>
              </List.Item>
            )}
          />
        </div>
      )}
    </div>
  );
};
