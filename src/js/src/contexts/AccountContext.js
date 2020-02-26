import React, { createContext, useState, useEffect } from 'react';
import { getAccounts } from '../client';

export const AccountContext = createContext();

const AccountContextProvider = props => {
  const [accounts, setAccounts] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      getAccounts().then(res =>
        res.json().then(accounts => {
          setAccounts(accounts);
          setLoading(false);
        })
      );
    };
    fetchData();
  }, []);

  return (
    <AccountContext.Provider value={{ accounts, loading }}>
      {props.children}
    </AccountContext.Provider>
  );
};

export default AccountContextProvider;
