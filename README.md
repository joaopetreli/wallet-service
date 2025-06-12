# wallet-service
A wallet service that manages users' money. This service will support operations for depositing, withdrawing, and transferring funds between users.

# How to run
1. Make sure you have Docker installed on your machine.
2. Clone the repository
3. In the root directory of the project, run the following command:

```docker-compose up```
4. The service will be available at `http://localhost:8080`.
5. Now you can use the API endpoints to manage users' wallets and perform operations like deposit, withdraw, and transfer funds.

# API Endpoints
## Create a Wallet
```
curl --request POST \
  --url http://localhost:8080/wallets
```
## Deposit Funds
```
curl --request POST \
  --url http://localhost:8080/funds/deposit \
  --header 'Content-Type: application/json' \
  --data '{
	"accountNumber": 1,
	"amount": 1000
}'
```
## Withdraw Funds
```
curl --request POST \
  --url http://localhost:8080/funds/withdraw \
  --header 'Content-Type: application/json' \
  --data '{
	"accountNumber": 1,
	"amount": -100
}'
```
## Transfer Funds
```
curl --request POST \
  --url http://localhost:8080/funds/transfer \
  --header 'Content-Type: application/json' \
  --data '{
	"fromAccountNumber": 1,
	"toAccountNumber": 2,
	"amount": 100
}'
```
## Get Wallet Balance
```
curl --request GET \
  --url http://localhost:8080/balance/1
```
## Retrieve Historical Balance
```
curl --request GET \
  --url 'http://localhost:8080/balance/2/historical-balance?startDate=2025-05-01T00%3A00%3A00&endDate=2025-05-31T23%3A59%3A59'
```