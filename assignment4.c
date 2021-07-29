#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <stdbool.h>
#include <string.h>

/*Assignment 4
  Vinay Kumar Sharma*/

//my structure

	typedef struct mydata 
		{
		  int id;
		  char name [26] ;
		  int age;
		  char classSample[26];
		  int c1;
		  int c2;
		  int c3;
		  int c4;
		  int total;
		  float per;
		  struct mydata *next;
		} Node;
	

	Node * insertBegin(int pNum,  char name [26] , int q ,char classSamplee [26] ,int cr1,int cr2,int cr3,int cr4,Node *head) 
		{
		 	Node *new_node;
		 	new_node = (Node *) malloc(sizeof(Node));               //to insert at the begining
		 	new_node->id = pNum;
		 	strcpy(new_node->name, name);
		 	new_node->age = q;
		 	strcpy(new_node->classSample, classSamplee);
		 	new_node->c1 = cr1;
		 	new_node->c2 = cr2;
		 	new_node->c3 = cr3;
		 	new_node->c4 = cr4;
		 	new_node->next= head;
			head = new_node;
			return head;
		}


	Node * insertLast(int pNum,  char name [26] , int q ,char classSamplee [26] ,int cr1,int cr2,int cr3,int cr4,Node *head) 
		{
		  	Node *current_node = head;
		  	Node *new_node;
			 while ( current_node != NULL && current_node->next != NULL)   //to insert at last
			 {
		   		current_node = current_node->next;
	         }
		   	new_node = (Node *) malloc(sizeof(Node));
		  	new_node->id = pNum;
		 	strcpy(new_node->name, name);
		 	new_node->age = q;
		 	strcpy(new_node->classSample, classSamplee);
		 	new_node->c1 = cr1;
		 	new_node->c2 = cr2;
		 	new_node->c3 = cr3;
		 	new_node->c4 = cr4;
		  	new_node->next= NULL;
		  	
  		if (current_node != NULL)
	  		{
	    	 current_node->next = new_node;
	   		}
	  	else
			{ 
		     head = new_node;
		    }
	return head;
}

	void insertAt(int pNum,  char name [26] , int q ,char classSamplee [26],int cr1,int cr2,int cr3,int cr4,int val, Node* head)
		{
			Node *temp1 = (Node *) malloc(sizeof(Node));                   //to insert anywhere	
		    temp1->id = pNum;
		 	strcpy(temp1->name, name);
		 	temp1->age = q;
		 	strcpy(temp1->classSample, classSamplee);
		 	temp1->c1 = cr1;
		 	temp1->c2 = cr2;
		 	temp1->c3 = cr3;
		 	temp1->c4 = cr4;
			temp1->next=NULL;
			if(val==1)
				{
					temp1->next=head;
					head=temp1;
					return;
				}
			Node *temp2=head;
			
			int i;
			for(i=0;i<val-2;i++)
				{
					temp2=temp2->next;
				}
					
				temp1->next=temp2->next;
				temp2->next=temp1;
		}

	void deleteBegin(Node **head)
		{
			if(head==NULL)
				{
					printf("database is empty please insert element first");    //delete the first element
					return;
				}
				
			Node *temp=*head;
			*head=temp->next;
			free(temp);
			
		}

	void deleteEnd( Node *head)
		{
			Node *temp1=head;             //delete at the end
			int i;
			for(i=0;i<(temp1!=NULL);i++)
				{
					temp1=temp1->next;
				}
			Node *temp2=temp1->next;
			temp1->next=temp2->next;
			free(temp2);
		}

	void deleteAt(int val, Node *head)
		{
			Node *temp1=head;                          //delete any element
			int i;
			for(i=0;i<val-2;i++)
				{
					temp1=temp1->next;
				}
			Node *temp2=temp1->next;
			temp1->next=temp2->next;
			free(temp2);
		}

	
	void searchList(Node *head, int n)
    {
		if(head==NULL)
		{
		
			printf("Please fill the list first");
			return;
					}
	  Node *p;
	  for (p = head; p != NULL; p = p->next){
	  	int count=1;
	  	count++;
		if (p->id == n)
		{
				printf("Student with ID %d is at position %d.!!!!  \n",n,count);
				return;
		}
	}
	printf("Cannot Find your input\n");
}
		

	void print(Node *head) {
 	Node *current_node = head;
  	int count =1;
  	while ( current_node != NULL)
		 {
		 	int per;
		 	int total;
		 	total=current_node->c1+current_node->c2+current_node->c3+current_node->c4;
		 	per=total/4;
		 	current_node->total=total;
		 	current_node->per=per;
	    	printf("\n%d)\tID = %d\n\tStudent name = %s\n\tAge= %d\n\tclass = %s\n\tCourse1 = %d\n\tCourse2 = %d\n\tCourse3 = %d\n\tCourse4 = %d\n\tTotal = %d\n\tPercentage = %f\n"
			, count,current_node->id,current_node->name,current_node->age,current_node->classSample,current_node->c1,current_node->c2,current_node->c3,current_node->c4,current_node->total,current_node->per);    //code to print 
	    	count++;
	    	current_node = current_node->next;
	     }
}

	void Insertionsort(int numberofNodes,Node *head)     //to search
	{
		int nodeCtr;
		int ctr;
		int pnodeDataCopy;
		int pqnodeDataCopy;
		int crs1;
		int crs2;
		int crs3;
		int crs4;
		char pnamee[26];
		char ClassSamplee[26];
		Node *currentNode;
	    Node *nextNode;
		
		for(nodeCtr= numberofNodes-2;nodeCtr>=0;nodeCtr--)
		{
		 currentNode=head;
		 nextNode = currentNode->next;
		 
		 for(ctr=0;ctr<=nodeCtr;ctr++)
		 {
		 	if(currentNode->id > nextNode->id)
		 	{
		 		pnodeDataCopy=currentNode->id;
		 			pqnodeDataCopy=currentNode->age;
		 				strcpy(pnamee, currentNode->name);
		 			    	strcpy(ClassSamplee, currentNode->classSample);
		 			    	 crs1=currentNode->c1;
		 			    	 crs2=currentNode->c2;
		 			    	 crs3=currentNode->c3;
		 			    	 crs4=currentNode->c4;
		 			    	
		 		currentNode->id=nextNode->id;
		 		currentNode->age=nextNode->age;
		 		strcpy(currentNode->name,nextNode->name);
		 		strcpy(currentNode->classSample,nextNode->classSample);
		 		nextNode->id= pnodeDataCopy;
		 		nextNode->age=pqnodeDataCopy;
		 		strcpy(nextNode->name,pnamee);
		 		strcpy(nextNode->classSample,ClassSamplee);
		 		nextNode->c1= crs1;
		 		nextNode->c2= crs2;
		 		nextNode->c3= crs3;
		 		nextNode->c4= crs4;
			 }
			 currentNode=nextNode;
			 nextNode=nextNode->next;
		 }
		}
	}
	
		void quicksort(int numberofNodes,Node *head) {
		int nodeCtr;
		int ctr;
		int pnodeDataCopy;
		int pqnodeDataCopy;
		int crs1;
		int crs2;
		int crs3;
		int crs4;
		char pnamee[26];
		char ClassSamplee[26];
		Node *currentNode;
	    Node *nextNode;
		
		for(nodeCtr=numberofNodes-2;nodeCtr>=0;nodeCtr--)
		{
		 currentNode=head;
		 nextNode = currentNode->next;
	     Node* pivot = head;
		 for(ctr=0;ctr<=nodeCtr;ctr++)
		 {
		 	if(currentNode->id > nextNode->id)
		 	{
		 		pnodeDataCopy=currentNode->id;
		 		pqnodeDataCopy=currentNode->age;
		 		strcpy(pnamee, currentNode->name);
		 		strcpy(ClassSamplee, currentNode->classSample);
		 		crs1=currentNode->c1;
		 	    crs2=currentNode->c2;
		 		crs3=currentNode->c3;
		 		crs4=currentNode->c4;
		 			    	
		 		currentNode->id=nextNode->id;
		 		currentNode->age=nextNode->age;
		 		strcpy(currentNode->name,nextNode->name);
		 		strcpy(currentNode->classSample,nextNode->classSample);
		 		nextNode->id= pnodeDataCopy;
		 		nextNode->age=pqnodeDataCopy;
		 		strcpy(nextNode->name,pnamee);
		 		strcpy(nextNode->classSample,ClassSamplee);
		 		nextNode->c1= crs1;
		 		nextNode->c2= crs2;
		 		nextNode->c3= crs3;
		 		nextNode->c4= crs4;
			 }
			 currentNode=nextNode;
			 nextNode=nextNode->next;
		 }
		}
		 
		}
	

		int main()
		{
		   Node *head = NULL;
		   int pNum,qty, pos,crss1,crss2,crss3,crss4;
		   int total=0;
		   char namee[26];
		   char classe[26];
		   int option;
		   char * temp;
		   
		   while(true) {
		
											  printf("\t\t+============================================+\n");
											  printf("\t\t|      Assignment4 (Vinay Kumar Sharma)      |\n");
			/*Prog Layout*/					  printf("\t\t+============================================+\n");
				   						      printf("\t\t|  1) Display Students                       |\n");
										      printf("\t\t|  2) Insert Student At Begining             |\n");
								   		      printf("\t\t|  3) Insert Student At End                  |\n");
								    		  printf("\t\t|  4) Insert Student Anywhere                |\n");
										      printf("\t\t|  5) Delete Student At Begining             |\n");
										      printf("\t\t|  6) Delete Student At End                  |\n");
										      printf("\t\t|  7) Delete Anything                        |\n");
										      printf("\t\t|  8) Search Students                        |\n");
										      printf("\t\t|  9) Sort Students(Insertion sort)          |\n");
										      printf("\t\t| 10) Sort Students(QuickSort)               |\n");
										      printf("\t\t|  0) Exit                                   |\n");
										      printf("\t\t+============================================+\n");
		     
		     printf("\nSelect the options shown above : ");
		     
		     //data(user input) validation is done here
		     if (scanf("%d", &option) != 1) {
		        printf(" *Error: Invalid input Entered. Enter a number.\n");
		        scanf("%s", &temp);
		        continue;
		     }
		     
		     switch (option) {
		      case 1: //shows all element present in the list
		          printf("\nElements inside the database: \n ");
		          print(head);
		          printf("\n\nPress any key to continue....\n");
		          getch();
		          break;
		
		      case 2:  //insert at first location
		          printf(" Enter a ID to insert : ");
		          if (scanf("%d", &pNum) != 1) {
		              printf(" *Error: Invalid input.Enter a number\n");
		              scanf("%s", &temp);   /*clear input buffer */
		              continue;
		          }
		           printf(" Enter Student age : ");
		          scanf("%d", &qty);
		           printf(" Enter Student name : ");
		          scanf("%s", &namee);
		          printf(" Enter Class : ");
		          scanf("%s", &classe);
		          printf(" Enter Course1 Marks : ");
		          scanf("%d", &crss1);
		          printf(" Enter Course2 Marks : ");
		          scanf("%d", &crss2);
		          printf(" Enter Course3 Marks : ");
		          scanf("%d", &crss3);
		          printf(" Enter Course4 Marks : ");
		          scanf("%d", &crss4);
		          head = insertBegin(pNum,namee,qty,classe,crss1,crss2,crss3,crss4 ,head);
		          printf("Student %s added in the list", namee);
		          printf("\n\nPress any key to continue....\n");
		          getch();
		          total++;
		          break;
		
		      case 3:  //insert at last
		          printf(" Enter a ID to insert : ");
		          if (scanf("%d", &pNum) != 1 ) {
		              printf(" *Error: Invalid input.Enter a number \n");
		              scanf("%s", &temp); 
		              continue;
		          }
		          printf(" Enter Student age : ");
		          scanf("%d", &qty);
		           printf(" Enter Student name : ");
		          scanf("%s", namee);
		          printf(" Enter Class : ");
		          scanf("%s", &classe);
		          printf(" Enter Course1 Marks : ");
		          scanf("%d", &crss1);
		          printf(" Enter Course2 Marks : ");
		          scanf("%d", &crss2);
		          printf(" Enter Course3 Marks : ");
		          scanf("%d", &crss3);
		          printf(" Enter Course4 Marks : ");
		          scanf("%d", &crss4);
		          head = insertLast(pNum,namee,qty,classe,crss1,crss2,crss3,crss4 , head);
		          printf("item %s added in the list", namee);
		          printf("\n\nPress any key to continue....\n");
		          getch();
		          total++;
		          break;
		
		      case 4: //insert at user desire
		          printf(" Enter a ID to insert : ");
		          if (scanf("%d", &pNum) != 1) {
		              printf(" *Error: Invalid input.Enter a number.\n");
		              scanf("%s", &temp);  
		              continue;
		          }
		          printf(" Enter Student age : ");
		          scanf("%d", &qty);
		           printf(" Enter Student name : ");
		          scanf("%s", &namee);
		          printf(" Enter Class : ");
		          scanf("%s", &classe);
		           printf(" Enter Course1 Marks : ");
		          scanf("%d", &crss1);
		          printf(" Enter Course2 Marks : ");
		          scanf("%d", &crss2);
		          printf(" Enter Course3 Marks : ");
		          scanf("%d", &crss3);
		          printf(" Enter Course4 Marks : ");
		          scanf("%d", &crss4);
		
		          printf("Position you want to insert : ");
		          if (scanf("%d", &pos) != 1) {
		              printf(" *Error: Invalid input.Enter a number.\n");
		              scanf("%s", &temp);
		              continue;
		          }
		          
		              insertAt(pNum,namee,qty,classe,crss1,crss2,crss3,crss4, pos, head);
		              printf("%d is inserted at %d", pNum, pos);
		              printf("\n\nPress any key to continue....\n");
		              getch();
		              total++;
		              break;
		
		      
		      case 5: //delete first element
		           	
		           	deleteBegin(&head);
		           	printf("\n\nPress any key to continue....\n");
		          	  getch();
		          	  break;
		      case 6:  //delete last element
		           	
		           	deleteEnd(head);
		           	printf("\n\nPress any key to continue....\n");
		          	getch();
		          	break;
		          	           	  
		
		      case 7: //delete anything user want at any index
		           	printf(" Enter position of node you wish to delete: ");
		           	if (scanf("%d", &pos) != 1) 
					   {
		              	printf(" *Error: Invalid input.Enter a number\n");
		              	scanf("%s", &temp);
		              	continue;
		          		}
		           	if(pos==1)
		           		deleteBegin(&head);
		           	else
		           		deleteAt(pos,head);
		            getch();
		            break;
		       case 8:   //search for an elemnt
		        {          	
		           	printf(" enter number to search in list : ");
		           	if (scanf("%d", &pNum) != 1) 
				 	 {
		              	printf(" *Error: Invalid input.Enter a number\n");
		              	scanf("%s", &temp);  
		              	continue;
		             }
		           	 searchList(head,pNum) ;
		          	  getch();
		          	  break;
		        }
		        
		         case 9:   //search for an elemnt
		        {          	
		           	Insertionsort(total,head);
		           	printf("Items are sorted in accending order based on their 'ID'./n And Number of operation to complete it is %d",total-1);
		            printf("\n\nPress any key to continue....\n");
		            getch();
		           	break;
		        }
		        
		        case 10:   //search for an elemnt
		        {          	
		           	quicksort(total,head);
		           	printf("Items are sorted in accending order based on their 'ID'./n And Number of operation to complete it is %d",total/2);
		            printf("\n\nPress any key to continue....\n");
		            getch();
		           	break;
		        }
		
		      case 0: 
		          	return(0);
		          	break;
		          
		      
		      default:
		          	printf("Invalid Selection. Please Try again(Enter the index number infront of option).");
		          	getch();
		
		      } 
		   } 
		
		return(0);
		}

