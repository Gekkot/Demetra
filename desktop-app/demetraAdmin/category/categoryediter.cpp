#include "categoryediter.h"

#define HEIGHT (30)
#define WIDTH (300)

categoryEditer::categoryEditer(QWidget *parent) : QWidget(parent){

    this->setMinimumSize(WIDTH,HEIGHT);

    nameCategoryLineEdit = new QLineEdit(this);
    nameCategoryLineEdit->setGeometry(0,0,WIDTH*0.75,HEIGHT);

    editButton = new QPushButton(this);
    editButton->setGeometry(nameCategoryLineEdit->x()+nameCategoryLineEdit->width()+5,0,(WIDTH-WIDTH*0.75),HEIGHT);
    editButton->setText("Сохранить");
    connect(editButton,SIGNAL(clicked()),this,SLOT(editButtonClicked()));

}

void categoryEditer::editButtonClicked(){
    emit changeCategoryName(nameCategoryLineEdit->text());
    this->close();
}

