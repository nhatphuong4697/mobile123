import { Component,OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {App} from './app';
import { ArticleService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  //Component properties
  allMobiles: App[];
  statusCode: number;
  requestProcessing = false;
  articleIdToUpdate = null;
  processValidation = false;
  //Create form
  appForm = new FormGroup({
    namemobile: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
    cost: new FormControl('', Validators.required),
    image: new FormControl('', Validators.required)
  });
  //Create constructor to get service instance
  constructor(private articleService: ArticleService) {
  }
  //Create ngOnInit() and and load articles
  ngOnInit(): void {
    this.getAllArticles();
  }
  //Fetch all articles
  getAllArticles() {
    this.articleService.getAllMobile()
      .subscribe(
        data => this.allMobiles = data,
        errorCode =>  this.statusCode = errorCode);
  }
  //Handle create and update article
  onArticleFormSubmit() {
    this.processValidation = true;
    if (this.appForm.invalid) {
      return; //Validation failed, exit from method.
    }
    //Form is valid, now perform create or update
    this.preProcessConfigurations();
    let namemobile = this.appForm.get('namemobile').value.trim();
    let description = this.appForm.get('description').value.trim();
    let cost = this.appForm.get('cost').value.trim();
    let image = this.appForm.get('image').value.trim();
    if (this.articleIdToUpdate === null) {
      //Handle create article
      let mobile= new App(null, namemobile, description,cost,image);
      this.articleService.createMobile(mobile)
        .subscribe(successCode => {
            this.statusCode = successCode;
            this.getAllArticles();
            this.backToCreateArticle();
          },
          errorCode => this.statusCode = errorCode);
    } else {
      //Handle update article
      let mobile= new App(this.articleIdToUpdate, namemobile, description,cost,image);
      this.articleService.updateMobileById(mobile)
        .subscribe(successCode => {
            this.statusCode = successCode;
            this.getAllArticles();
            this.backToCreateArticle();
          },
          errorCode => this.statusCode = errorCode);
    }
  }

  loadArticleToEdit(mobileid: string) {
    this.preProcessConfigurations();
    this.articleService.getMobileById(mobileid)
      .subscribe(mobile => {
          this.articleIdToUpdate = mobile.mobileid;
          this.appForm.setValue({ id: mobile.mobileid,name: mobile.name_mobile, descrpition: mobile.decription, cost: mobile.cost,  image: mobile.image });
          this.processValidation = true;
          this.requestProcessing = false;
        },
        errorCode =>  this.statusCode = errorCode);
  }
  //Delete article
  deleteArticle(articleId: string) {
    this.preProcessConfigurations();
    this.articleService.deleteMobileById(articleId)
      .subscribe(successCode => {
          this.statusCode = successCode;
          this.getAllArticles();
          this.backToCreateArticle();
        },
        errorCode => this.statusCode = errorCode);
  }

  preProcessConfigurations() {
    this.statusCode = null;
    this.requestProcessing = true;
  }
  //Go back from update to create
  backToCreateArticle() {
    this.articleIdToUpdate = null;
    this.appForm.reset();
    this.processValidation = false;
  }
}
