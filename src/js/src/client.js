const checkStatus = response => {
  if (response.ok) {
    return response;
  } else {
    let error = new Error(response.statusText);
    error.response = response;
    response.json().then(e => {
      error.error = e;
    });
    return Promise.reject(error);
  }
};
export const getTransactions = accountId =>
  fetch(`/api/transactions/${accountId}`).then(checkStatus);

export const getAccounts = () => fetch('api/accounts').then(checkStatus);


export const transferMoney = payment =>
  fetch('api/payment/', {
    headers: {
      'Content-Type': 'application/json'
    },
    method: 'POST',
    body: JSON.stringify(payment)
  }).then(checkStatus);
