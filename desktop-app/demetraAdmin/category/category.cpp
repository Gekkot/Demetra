#include "category.h"

#define CATEGORY_HEIGHT (50)
#define CATEGORY_WIDTH (400)

Category::Category(QWidget *parent,QString categoryName) : QWidget(parent)
{
    this->setMinimumSize(CATEGORY_WIDTH,CATEGORY_HEIGHT);

    categoryNameButton = new QPushButton(this);
    categoryNameButton->setGeometry(0,CATEGORY_HEIGHT/10,CATEGORY_WIDTH*0.6,CATEGORY_HEIGHT-(CATEGORY_HEIGHT/10)-(CATEGORY_HEIGHT/10));
    categoryNameButton->setText(categoryName);
    categoryNameButton->setCursor(Qt::PointingHandCursor);
    connect(categoryNameButton,SIGNAL(clicked()),this,SLOT(categoryNameButtonClicked()));
    int previously_x = categoryNameButton->x()+categoryNameButton->width();

    editNameButton = new QPushButton(this);
    editNameButton->setGeometry(previously_x,CATEGORY_HEIGHT/10,(CATEGORY_WIDTH-(CATEGORY_WIDTH*0.75))/2,CATEGORY_HEIGHT-(CATEGORY_HEIGHT/10)-(CATEGORY_HEIGHT/10));
    editNameButton->setStyleSheet("border-image: url(:/img/editIcon.png);");
    connect(editNameButton,SIGNAL(clicked()),this,SLOT(editNameButtonEvent()));
    editNameButton->setCursor(Qt::PointingHandCursor);

    previously_x = editNameButton->x()+editNameButton->width();

    addPositionButton = new QPushButton(this);
    addPositionButton->setText(tr("Добавить позицию"));
    addPositionButton->setGeometry(previously_x,CATEGORY_HEIGHT/10,(CATEGORY_WIDTH-(((CATEGORY_WIDTH*0.6))/2)*2),CATEGORY_HEIGHT-(CATEGORY_HEIGHT/10)-(CATEGORY_HEIGHT/10));
    addPositionButton->setCursor(Qt::PointingHandCursor);
    connect(addPositionButton,SIGNAL(clicked()),this,SLOT(addPositionButtonClicked()));

    previously_x = addPositionButton->x()+addPositionButton->width();

    deleteCategoryButton = new QPushButton(this);
    deleteCategoryButton->setGeometry(previously_x,CATEGORY_HEIGHT/10,(CATEGORY_WIDTH-(CATEGORY_WIDTH*0.75))/2,CATEGORY_HEIGHT-(CATEGORY_HEIGHT/10)-(CATEGORY_HEIGHT/10));
    deleteCategoryButton->setStyleSheet("border-image: url(:/img/deleteIcon.png);");
    deleteCategoryButton->setCursor(Qt::PointingHandCursor);
    connect(deleteCategoryButton,SIGNAL(clicked()),this,SLOT(deleteButtonEvent()));



}

QString Category::getCategoryName(){
    return(categoryNameButton->text());
}

void Category::deleteButtonEvent(){
    emit deleteCategoryLater();
}

void Category::addPositionButtonClicked()
{
    qDebug()<<"CLICKED!!!!! ADD POSITION";
    emit addPositionButtonEvent();

}

void Category::categoryNameButtonClicked(){
    qDebug()<<"in category...send emit....categoryNameButtonClicked()";
    emit categoryViewClicked();
}

void Category::editNameButtonEvent(){
    categoryEditer* editer = new categoryEditer(nullptr);
    editer->show();
    connect(editer,SIGNAL(changeCategoryName(QString)),this,SLOT(editName(QString)));
}

void Category::editName(QString newName){
   categoryNameButton->setText(newName);
   this->setObjectName(newName);
}

