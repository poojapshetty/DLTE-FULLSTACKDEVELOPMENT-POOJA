var transac = []    //Array that stores transaction amounts
var cnt = 0;    //Keep count of transactions (Can also be used for indexing)
var debCnt = 0; //Count the number of debit transactions
function limit(){
    let amt = document.forms['lim'].amount.value;
    document.forms['lim'].amount.value = "";
    transac[cnt] = +amt;    //Pushing the amount to the array
    //The first transaction
    if(cnt==0){ 
        alert(amt +" credited.");
    }
    //The remaining transactions
    if(cnt>0){
        //Checking if the current transaction amount is less than the previous one
        if(transac[cnt]<=transac[cnt-1]){
            //Checking if the number of debit transactions have crossed 3
            if(debCnt>4){
                    alert("Debit limit reached\nYou will be charged Rs.20 extra");
                    alert(amt+" debited.")
                    debCnt += 1;
                }
            else{
                alert(amt+" debited.")
                debCnt += 1;
            }
        }
        else{
            alert(amt +" credited.")
        }
    }
    //Writing the history to the html
        cnt += 1;
}
