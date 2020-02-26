import React, { useState, useEffect } from 'react';
import { List, Avatar, Icon, Spin } from 'antd';
import { useParams } from 'react-router-dom';
import { getTransactions } from '../client';

export const AccountTransaction = () => {
  let { id } = useParams();
  const [transactions, setTransaction] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      getTransactions(id)
      .then(res =>
        res.json().then(transactions => {
          setTransaction(transactions);
          setLoading(false);
        })
      );
    };
    fetchData();
  }, [id]);

  const getIndicatorIcon = () => (
    <Icon type='loading' className='spinner' spin />
  );

  return (
    <div>
      <h1 className='pt-5'>Account Transaction Page</h1>
      {loading ? (
        <Spin indicator={getIndicatorIcon()} />
      ) : (
        <List
          itemLayout='horizontal'
          dataSource={transactions}
          renderItem={item => (
            <List.Item className='pt-5'>
              <List.Item.Meta
                avatar={
                  <Avatar style={{ backgroundColor: '#87d068' }} icon='dollar' />
                }
                title={item.toAccountName}
                description={item.amount}
              />
            </List.Item>
          )}
        />
      )}
    </div>
  );
};

export default AccountTransaction;
