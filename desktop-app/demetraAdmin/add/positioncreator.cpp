#include "positioncreator.h"
#include "ui_positioncreator.h"

#define HEIGHT (30)
#define WIDTH (300)

positionCreator::positionCreator(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::positionCreator)
{
    ui->setupUi(this);
}

positionCreator::~positionCreator()
{
    delete ui;
}

void positionCreator::on_saveButton_clicked()
{
  emit savePosition(ui->nameLineEdit->text(),ui->costLineEdit->text());
    this->close();
}
