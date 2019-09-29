#include "categorycreator.h"

#define HEIGHT (30)
#define WIDTH (300)


categoryCreator::categoryCreator(QWidget *parent) : QDialog(parent)
{
    this->setMinimumSize(WIDTH,HEIGHT);

    nameCategoryLineEdit = new QLineEdit(this);
    nameCategoryLineEdit->setGeometry(0,0,WIDTH*0.75,HEIGHT);

    saveButton = new QPushButton(this);
    saveButton->setGeometry(nameCategoryLineEdit->x()+nameCategoryLineEdit->width()+5,0,(WIDTH-WIDTH*0.75),HEIGHT);
    saveButton->setText("Сохранить");
    connect(saveButton,SIGNAL(clicked()),this,SLOT(saveButtonClicked()));

}

void categoryCreator::saveButtonClicked(){
    emit saveCategoryName(nameCategoryLineEdit->text());
    this->close();
}

